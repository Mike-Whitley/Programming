
<template>



  <dev>
    <dev>
      <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a class="btn btn-primary" href="/petitions">Home</a>
          </li>
          <li>
            <a class="btn btn-primary"  href="/register" id="regbuttonid">Register</a>
          </li>

        </ul>
      </nav>
    </dev>


    <h5>Required</h5>
    <input v-model="email" placeholder="Email" >
    <input v-model="password" placeholder="Password" :type="'password'">
    <button type="button" class="btn btn-primary" v-on:click.prevent="LoginUser()">
      Log in</button>
    <label id="usernameErrorLabel" style="color:red" v-show="check()" ref="nameErrorLabel">{{ errorgiven }}</label>

  </dev>


</template>

<script>

  const SERVER_URL = 'http://localhost:4941/api/v1/'

  export default {
    data(){
      return{
        errorgiven: "",
        errorFlag: false,
        email: "",
        password: "",
        truthcheck: false,
      }
    },
    mounted: function() {
    },
    methods: {

      LoginUser: function () {
          this.$http.post(SERVER_URL+ 'users/login', {
            email: this.email, password: this.password
          })
            .then(response => {
              localStorage.setItem('token',response.data.token);
              localStorage.setItem('userId',response.data.userId);
              this.$router.push('/petitions')
            })
            .catch((error) => {
              this.truthcheck = true
              this.errorgiven = "invalid username or password"


          });

        },


      check: function (){

        if (this.email == "" || this.password == "" || localStorage.getItem('token') == null) {
          this.error = "please enter a valid username and password"
          return true
        }else{
          return false
        }
      }


    }
  }


</script>

<style scoped>

</style>
