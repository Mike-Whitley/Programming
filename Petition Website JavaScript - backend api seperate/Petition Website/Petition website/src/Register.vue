<template>




  <dev>

    <dev>
      <!-- start of navigation bar -->
      <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a class="btn btn-primary" href="/petitions">Home</a>
          </li>
          <li>
            <a class="btn btn-primary" href="/login" id="loginidbutton">Login</a>
          </li>

        </ul>
      </nav>

      <!------------------End of navigation bar----------------------->
      </dev>


    <dev>
      <h5>Required</h5>
      <input v-model="sqlreg.name" placeholder="Name">
      <!----- <p>Username is: {{ usernameadd }}</p>--------->
      <input v-model="sqlreg.password" placeholder="Password" :type="'password'">
      <input v-model="sqlreg.email" placeholder="Email" >
      <br/><br/>
      <p>Optional details</p>
      <input v-model="sqlreg.city" placeholder="City">
      <input v-model="sqlreg.country" placeholder="Country">
      <br/><br/>
      <p>User Photo</p>
      <b-form-file accept=".jpg, .png, .gif, .jpg" v-model="file"></b-form-file>
      <button type="button" class="btn btn-primary" v-on:click.prevent="createSQL()">
        Register</button>
      <label id="usernameErrorLabel" style="color:red" hidden="true" ref="nameErrorLabel">{{error}}</label>

    </dev>
  </dev>

  <!-----------
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AddUserModal">Register Account</button> --->

    <!------------register user end goes below button------------------>
    <!-----------
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
            <h5>Required</h5>
            <input v-model="sqlreg.name" placeholder="Name">

              <input v-model="sqlreg.password" placeholder="Password" :type="'password'">
              <input v-model="sqlreg.email" placeholder="Email" >
              <br/><br/>
              <p>Optional details</p>
              <input v-model="sqlreg.city" placeholder="City">
              <input v-model="sqlreg.country" placeholder="Country">
              <br/><br/>
              <p>User Photo</p>
              <input type='file' @change='onFileSelected'>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">
              Close
            </button>

            <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click.prevent="createSQL()">
              Register
            </button>
            <label id="usernameErrorLabel" style="color:red" hidden="true" ref="nameErrorLabel"></label>
          </div>

        </div>
      </div>

    </div>
    ----------->
<!------------register user end------------------>

  </dev>

</template>

<script>

  const SERVER_URL = 'http://localhost:4941/api/v1/'

  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
        newerrorFlag: false,
        usernameadd: "",
        emailadd: "",
        passwordadd: "",
        addcountry: "",
        addcity: "",
        file: null,
        id: 30,
        sqlreg: {
          name: "",
          email: "",
          password: "",
          city: "",
          country: "",
        }




      }
    },
    mounted: function() {
    },
    methods: {


      onFileSelected: function(event){
        this.selectedFile = event.target.files[0]
      },


      createSQL: function(){

        let error = document.getElementById("usernameErrorLabel")

        if (this.sqlreg.password == ""){
          this.error = "password field must not be empty"
          console.log("error in password blank")
          error.hidden = false
          return
        }
        if (this.sqlreg.email.includes("@") == false){
          this.error = "Invalid email it must contain @ symbol"
          console.log("error in email @")
          error.hidden = false
          return
        }
        if (this.sqlreg.city == ""){
          delete this.sqlreg['city']
        }

        if (this.sqlreg.country == ""){
          delete this.sqlreg['country']
        }
        if (this.newerrorFlag == false) {
          this.RegisterUser()
          console.log("success")
          //data-dismiss
        }

      },

      RegisterUser: function(){
        this.$http.post(SERVER_URL+ 'users/register', this.sqlreg)
          .then((response) => {
            console.log("response = " + JSON.stringify(response.data) )
            return this.$http.post(SERVER_URL+ 'users/login', {
              email: this.sqlreg.email, password: this.sqlreg.password
            })

          })
          .then(response => {
            localStorage.setItem('token',response.data.token)
            localStorage.setItem('userId',response.data.userId)
            if(this.file != null){
              console.log("file is not null")
              this.setImage()
            }
            console.log("value = ",localStorage.getItem('token'))

            this.$router.push('/petitions')
          })
          .catch((error) => {
            this.error = "invalid Email: already in use or missing domain name"
            error.hidden = false

          });

      },


      setImage: function (){
        ///petitions/:id/photo'
        const locid = localStorage.getItem('userId')
        const token = localStorage.getItem('token')  ///petitions/:id/photo
        console.log()
        console.log('thisis a test url = ' + 'http://localhost:4941/api/v1/users/' + locid + '/photo')
        console.log("the selected file is", this.file)
        this.axios.defaults.headers.common['X-Authorization'] = localStorage.getItem('token')
        this.axios.put('http://localhost:4941/api/v1/users/' + locid + '/photo',this.file, {headers: {
            "Content-Type": this.file.type}
        }) . catch(function (error) {
          alert(error);

        })
      },










      //this line below stops methods
    }
  }



</script>

<style scoped>

</style>
