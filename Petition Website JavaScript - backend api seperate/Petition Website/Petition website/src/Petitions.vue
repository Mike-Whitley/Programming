<template>
  <div>
    <div v-if="errorFlag" style="color: red;">
      {{ error }}
    </div>
<!------------- example of card
    <div>
      <div class="card" style="width: 18rem;">
        <img src="./assets/hello_there.jpg" class="card-img-top">
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" class="card-link">Card link</a>
          <a href="#" class="card-link">Another link</a>
        </div>
      </div>
    </div>
-----------card example --->
    <!-- start of navigation bar -->
    <nav class="navbar navbar-expand-sm bg-primary navbar-dark" >
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="btn btn-primary" href="/Petitions">Home</a>
        </li>

        <li>
          <a class="btn btn-primary"  href="/login" id="loginidbutton">Login</a>
        </li>

        <li>
          <a class="btn btn-primary"  href="/register" id="regbuttonid">Register</a>
        </li>

        <li>
          <a class="btn btn-primary" v-on:click="logout()" id="logoutbuttonid">logout</a>
        </li>


        <li>
          <a class="btn btn-primary"  href="/createpetition" id="createpetitionid">Create Petition</a>
        </li>

        <li>
          <a class="btn btn-primary"  href="/viewprofile" id="viewprofileid">View Profile</a>
        </li>

      </ul>
    </nav>

    <!------------------End of navigation bar----------------------->
    <div id="petitions">
      <table>
        <div class="card-deck"> <!---------this isngle line groups cards ------->
        <tr v-for="petition in petitions">
          <td>
          <!--------------------card starts here-------https://getbootstrap.com/docs/4.3/components/card/---------------------------->
          <div>
            <div class="card" style="width: 50rem;">
              <img :src="getPetitionsPhotos(petition.petitionId)" class="card-img-top">
              <div class="card-body">
                <h5 class="card-title">{{ petition.title }}</h5>
                <h6 class="card-subtitle mb-2 text-muted">Category: {{ petition.category }} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Author: {{petition.authorName}} </h6>
                <p class="card-text">Number of Signatures: {{petition.signatureCount}}</p>
                <router-link :to = "{ name: 'individualpetitions', params: { petitionId: petition.petitionId
              }}" tag="button" class="btn btn-primary">More Information</router-link>

              </div>
            </div>
          </div>
          </td>


          <!----------------------https://getbootstrap.com/docs/4.3/components/card/------- card ends here---------------------->
        </tr>
        </div>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
        petitions: [],
        LoggedIn: null
      }
    },
    mounted: function () {
      console.log("value in petitions for local storage is = ",localStorage.getItem('token'))
      // this.loggedIn = localStorage.getItem("token") != null;
      this.loggedIn = localStorage.getItem("token")
      if(this.loggedIn !== null){
       document.getElementById('loginidbutton').hidden = true
        document.getElementById('regbuttonid').hidden = true
      }
      console.log("hhh", this.loggedIn)
      if(this.loggedIn == null){
        console.log("gdsgfdsgdsgds")
        document.getElementById('logoutbuttonid').hidden = true
        document.getElementById('createpetitionid').hidden = true
        document.getElementById('viewprofileid').hidden = true
      }
      this.getPetitions();


      //logoutbuttonid
    },
    methods: {
      getPetitions: function () {
        console.log("this.loggedIn", this.loggedIn)
        console.log("logged in is at:", this.loggedIn)
        this.$http.get('http://localhost:4941/api/v1/petitions')
        .then((response) => {
          this.petitions = response.data;
        })
        .catch((error) => {
          this.error = error;
          this.errorFlag = true;
        })
      },

      logout: function(){
        localStorage.removeItem("token")
        window.location.reload()
      },

      getPetitionsPhotos: function (id) {
        const x = 'http://localhost:4941/api/v1/petitions/' + id + '/photo'
        return x
      }
    },






    props: ['loggedin']



  };

</script>
