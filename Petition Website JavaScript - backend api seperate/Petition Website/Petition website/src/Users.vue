<template>
  <div>
    <div v-if = "errorFlag" style = " color : red ; ">
      {{ error }}
    </div>
    <div v-if = "$route.params.userId">
      <div id = "user">
        <router-link :to="{ name: 'users'}" > Back to Users </router-link>


        <br /><br />
        <table>
          <tr>
            <td> User ID </td>
            <td> Username </td>
          </tr>
          <tr>
            <td> {{ $route.params.userId }} </td>
            <td> {{ getSingleUser($route.params.userId).username }} </td>
          </tr>
        </table>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#deleteUserModal">Delete</button>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editUserModal">Update Details</button> <!-- new line for edit user-->
            <!---------------------------------------------------------------------------edit user modal referes to the div class below it gets reference from its data---------------------->
      </div>

      <div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="deleteUserModalLabel">Delete User</h5>

              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>

            </div>

            <div class="modal-body">
              Are you sure that you want to delete this user?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">
                Close
              </button>
              <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="deleteUser($route.params.userId)">
                Delete User
              </button>
            </div>
          </div>
        </div>
      </div>
      <!------code from here for edit user -------->
      <div class = "modal fade" id = "editUserModal" tabindex = "-1" role = "dialog"
           aria-labelledby = "deleteUserModalLabel" aria-hidden = "true">
        <div class = "modal-dialog" role = "document">
          <div class = "modal-content">
            <div class = "modal-header">
              <h5 class = "modal-title" id = "editUserModallabel" > Edit User </h5>
              <button type = "button" class = "close" data-dismiss = "modal" aria-label = "Close">
                <span aria-hidden = "true" > &times; </span>
              </button>
            </div>
            <!----------WHERE I ADVISE USERNAME FIELD--------->
            <div class = "modal-body">
              <input v-model="username" placeholder="Username">
              <p>Username is: {{ username }}</p>
            </div>
            <!----------END OF WHERE I ADVISE USERNAME FIELD--------->
            <div class = "modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="UpdateUsername($route.params.userId)">
                Confirm update
              </button>
              <button type = "button" class = "btn btn-secondary" data-dismiss = "modal">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
      <!------code from here for edit user ends -------->








    </div>
    <div v-else>
      <div id = "users">
        <table>
          <tr v-for = "user in users">
            <td> {{ user.username }} </td>
            <td><router-link :to = "{ name: 'user', params: { userId: user.user_id
              }}" > View </router-link></td>
          </tr>
        </table>
      </div>
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AddUserModal">Add User</button> <!-- new line for edit user-->
    </div>
    <div class="modal fade" id="AddUserModal" tabindex="-1" role="dialog" aria-labelledby="AddUserModal" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="AddUserModalLabel">Add User</h5>

            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>

          </div>

          <div class="modal-body">
            <div class = "modal-body">
              <input v-model="usernameadd" placeholder="Username">
              <p>Username is: {{ usernameadd }}</p>
            </div>
            Are you sure that you want to Add this user?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">
              Close
            </button>
            <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="Adduser()">
              Add user
            </button>
          </div>
        </div>
      </div>
    </div>













  </div>
</template>




<script>
  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
        users: [],
        username: "",
        usernameadd: ""
      }
    },
    mounted: function() {
      this.getUsers();
    },


    methods: {
      getUsers: function () {
        this.$http.get('http://localhost:3000/api/users')
          .then((response) => {
            this.users = response.data;
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;

          });

      },
      getSingleUser: function (id) {
        for (var i = 0; i < this.users.length; i++) {
          if (this.users[i].user_id == id) {
            return this.users[i];
          }
        }

      },
      deleteUser: function (user_id) {
        this.$http.delete('http://localhost:3000/api/users/' + user_id)
          .then((response) => {

            for (var i = 0; i < this.users.length; i++) {
              if (user_id == this.users[i].user_id) {
                this.users.splice(i, 1);
              }
            }

            this.$router.push('/users').catch((err) => {
            });

          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          });
      },

      UpdateUsername: function (user_id) {
        this.$http.put('http://localhost:3000/api/users/' + user_id, {'username': this.username, 'userId': user_id})
          .then((response) => {

            for (var i = 0; i < this.users.length; i++) {
              if (user_id == this.users[i].user_id) {  //this loop updates user details
                this.users.splice(i, 1);
              }
            }
            this.$router.push('/users').catch((err) => { //this bring us back to users
            });

          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          });

      },

      Adduser: function (user_id) {
        this.$http.post('http://localhost:3000/api/users/', {'username': this.usernameadd})
          .then((response) => {
            this.getUsers() //I call this so I update the user list so when we pull the page again its updated
            this.$router.push('/users').catch((err) => { //this bring us back to users
            });

          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
            console.log("error in AddUser prob requested to many users be added")
          });

      }








    }
  }
</script>
