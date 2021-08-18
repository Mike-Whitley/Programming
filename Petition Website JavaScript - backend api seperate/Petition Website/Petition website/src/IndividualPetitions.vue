<template>
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
        <li>
          <a class="btn btn-primary"  href="/register" id="regbuttonid">Register</a>
        </li>
        <li>
          <a class="btn btn-primary"  href="/createpetition" id="createpetitionid">Create Petition</a>
        </li>
      </ul>
    </nav>

    <!------------------End of navigation bar----------------------->
    <dev>
      <img style="max-width: 450px; max-height: 300px" :src="getPetitionsPhotos(petitions.petitionId)" class="card-img-top">
    </dev>
    <h1>Title: {{ petitions.title }}</h1>
    <div v-show="canIsign(petitions.closingDate)">
    <button id="signpetitionbutton" type="button" class="btn btn-primary"  v-on:click="Sign_the_petition()">     <!-----v-if="canIsign()"---->
      Sign Petition</button>
    </div>

    <h1>Description: {{ petitions.description }}</h1>
    <h1>Author: {{ petitions.authorName }}</h1>
    <p>city: {{userDetails.city}}</p>
    <p>country {{userDetails.country}}</p>
    <dev>
      <img style="max-width: 450px; max-height: 300px" :src="getAuthorPhotos(petitions.authorId)" class="card-img-top" onerror="this.src='https://cdn.clipart.email/574515d561696205717e2fce5aa2ad23_facebook-default-profile-picture-alternatives-female-similar-_620-389.jpeg'">
    </dev>
    <p>{{ getUserDetails(petitions.authorId) }}</p>
    <h1>Number of signatures: {{ petitions.signatureCount }}</h1>
    <h1>category: {{ petitions.category }}</h1>
    <h1>Created Date: {{ petitions.createdDate }} </h1>
    <h1>Closing Date: {{ petitions.closingDate }}</h1>
    <h1>People who signed this petition</h1>
     <div id = "signatures">
       <table>
         <tr v-for = "signature in signatures">
           <td>Name: {{ signature.name }} &nbsp&nbsp</td>
           <td>City: {{ signature.city }} &nbsp&nbsp</td>
           <td>Country: {{ signature.country }} &nbsp&nbsp</td>
           <img :src="getAuthorPhotos(signature.signatoryId)" class="card-img-top" width="100" height="100" onerror="this.src='https://cdn.clipart.email/574515d561696205717e2fce5aa2ad23_facebook-default-profile-picture-alternatives-female-similar-_620-389.jpeg'" />
           <br/><br/>

         </tr>
        <!-------- <img src="./assets/default.png" />    --->
       </table>
     </div>


  </dev>

</template>

<script>
  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
        petitions: [],
        userDetails : [],
        signatures: [],
        imageUrl: "",
        petid: "",
        createdd: "",
        endeddate: "",
        truthcheck: false,


      }
    },
    created() {
      this.petitionId = this.$route.params.petitionId;  //this retrieves the variables I passed through in the url or in this case the petition id
    },
    mounted: function() {
      this.loadNeeded()
      this.petid = this.petitionId;
      this.loggedIn = localStorage.getItem("token")
      if(this.loggedIn !== null){
        document.getElementById('regbuttonid').hidden = true
        document.getElementById('loginidbutton').hidden = true
        document.getElementById('signpetitionid').hidden = true
      }
    },

    methods: {

      loadNeeded: function(){
        this.getPetition();
        this.getPetitionsPhotos(this.petitionId);
        this.getSignatures(this.petitionId);
        console.log("insdie loaded pettion is", this.petitions)
        this.getUserDetails()
        this.canIsign()

      },

      getPetition: function () {
        console.log("i am now being called")
        this.$http.get('http://localhost:4941/api/v1/petitions/' + this.petitionId)
          .then((response) => {
            this.petitions = response.data;
            console.log("this.petitions is now inside the get petition functiion: ", this.petitions)
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          })
      },
      getPetitionsPhotos: function (id) {
        const x = 'http://localhost:4941/api/v1/petitions/' + id + '/photo'
        return x
      },
      getAuthorPhotos: function (id) {
        const x = 'http://localhost:4941/api/v1/users/' + id + '/photo'
        this.imageUrl = x
        return x
        // console.log(x)
      },
      getUserDetails: function (userid) {
        this.$http.get('http://localhost:4941/api/v1/users/' + userid)
          .then((response) => {
            this.userDetails = response.data;
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          })
      },

      getSignatures: function (sigId) {
        this.$http.get('http://localhost:4941/api/v1/petitions/' + sigId + '/signatures')
          .then((response) => {
            this.signatures = response.data;
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;

          });


      },

      Sign_the_petition: function(){
        const token = localStorage.getItem('token')
        // console.log("signing petition with pitid", this.petitionId)
        // console.log("signing petition with token", token)
        // console.log('this.petition_id',this.petitionId)
        this.$http.post('http://localhost:4941/api/v1/petitions/'+ this.petitionId +'/signatures', "",{headers: {
            'X-Authorization': token}
        })
        this.getSignatures()
        window.location.reload()
      },

      canIsign: function(date){
        const currentdate = Date.now();
        let closingdate = new Date(date)
        const locid = localStorage.getItem('userId')

        for (var i = 0; i < this.signatures.length; i++) {
          if(this.signatures[i].signatoryId == locid){
            this.truthcheck = true
          }
        }

        if(this.loggedIn == null){
          return false
        }else if(this.truthcheck) {
          return false
        }else{
            if(closingdate < currentdate){
              return false
            }else {
              const uid = localStorage.getItem('userId')
              return true
            }

        }
      },





    }
  }


</script>

<style>

</style>
