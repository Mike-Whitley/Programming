
<template>


  <dev>
    <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="btn btn-primary" href="/petitions">Home</a>
        </li>
        <li>
          <a class="btn btn-primary" href="/login" id="loginidbutton">Login</a>
        </li>
        <li>
          <a class="btn btn-primary"  href="/register" id="regbuttonid">Register</a>
        </li>
        <li>
          <a class="btn btn-primary"  href="/createpetition" id="createpetitionid">Create Petition</a>
        </li>

      </ul>
    </nav>
  <dev>
    <img style="max-width: 450px; max-height: 300px" :src="getAuthorPhotos(this.userident)" class="card-img-top" onerror="this.src='https://cdn.clipart.email/574515d561696205717e2fce5aa2ad23_facebook-default-profile-picture-alternatives-female-similar-_620-389.jpeg'">

  </dev>
  <dev>
  <h1>Name: {{this.userDetails.name}}</h1>
  <h1>Email: {{this.userDetails.email}} </h1>
  <h1>City: {{this.userDetails.city}}</h1>
  <h1>Country: {{this.userDetails.country}}</h1>
  </dev>
  </dev>
</template>

<script>
  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
        loggedIn: null,
        userident: "",
        userDetails: [],
      }
    },
    mounted: function() {
      this.userident = localStorage.getItem('userId')
      this.loggedIn = localStorage.getItem("token")
      this.getUserDetails(this.userident)
      console.log("a==============", this.userDetails.email)

      if(this.loggedIn !== null){
        document.getElementById('loginidbutton').hidden = true
        document.getElementById('regbuttonid').hidden = true
      }
      console.log("hhh", this.loggedIn)
      if(this.loggedIn == null){
        console.log("gdsgfdsgdsgds")
        document.getElementById('logoutbuttonid').hidden = true
        document.getElementById('createpetitionid').hidden = true
      }



    },
    methods: {

      getAuthorPhotos: function (id) {
        const x = 'http://localhost:4941/api/v1/users/' + id + '/photo'
        this.imageUrl = x
        return x
        // console.log(x)
      },
      getUserDetails: function (userid) {
        const token = localStorage.getItem('token')
        this.$http.get('http://localhost:4941/api/v1/users/' + userid, {headers: {
            'X-Authorization': token}})
          .then((response) => {
            this.userDetails = response.data;
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          })
      },


    }
  }


</script>

<style scoped>

</style>
