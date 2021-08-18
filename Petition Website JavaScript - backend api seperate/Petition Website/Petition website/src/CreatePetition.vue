
<template>
  <dev>
    <dev>
      <h5>Create Petition</h5>
      <input v-model="giventitle" placeholder="Title">
      <!----- <p>Username is: {{ usernameadd }}</p>--------->


      <!------------categories---------------->
      <select v-model="selectedValue"> <!-------------selected value is the value of the model selected--------->
        <option v-for="cat in givencatagories" v-bind:value="cat.categoryId">
          {{ cat.name }}
      </option>
      </select>


      <!------------categories---------------->
      <datepicker placeholder="Select Date" :format="DatePickerFormat" :disabledDates="disabledDates" v-model="date"></datepicker>
      <!------------------date----------------->



      <!------------------date end----------------->
      <input v-model="givendescription" placeholder="Description">
      <p>User Photo</p>
        <!----------- <input v-model="file">  @change='onFileSelected'------>
      <b-form-file accept=".jpg, .png, .gif, .jpg" v-model="file"></b-form-file>
      <button type="button" class="btn btn-primary" v-on:click.prevent="Create_petition()">
        Create Petition</button>
      <label id="usernameErrorLabel" style="color:red" hidden="true" ref="nameErrorLabel">{{error}}</label>

    </dev>
  </dev>

</template>

<script>

  import Datepicker from 'vuejs-datepicker';

  export default {

    components: {
      Datepicker
    },

    data(){
      return{
        error: "",
        errorFlag: false,
        giventitle: "",
        catagory: "",
        selectedFile: null,
        givencatagories: [],
        givendescription: "",
        selectedValue: "Animals",
        currentdate: null,
        DatePickerFormat: 'dd/MM/yyyy',
        date: "",
        file: null,
        newdate: "",
        disabledDates:{
          to: new Date()
        },
        sql:"",
        petition_id: "",

      }
    },
    mounted: function() {
      // Data Picker Initialization
      this.GetCategories();
      this.currentdate = new Date();
      console.log(this.currentdate)
    },
    methods: {

      onFileSelected: function(event){
        this.selectedFile = event.target.files[0]
      },

      GetCategories: function(){
        this.$http.get('http://localhost:4941/api/v1/petitions/categories')
          .then((response) => {
            this.givencatagories = response.data;
            console.log(this.givencatagories)
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          })
      },

      Create_petition: function(){
        const token = localStorage.getItem('token')
        //console.log("current date", this.date)
        if(this.date == ""){

         this.sql = {
          title: this.giventitle,
           description: this.givendescription,
           categoryId: this.selectedValue}

          }else{
          this.callDateFunction()
          console.log("date ois notwwew this ", this.date)
          this.sql ={
            title: this.giventitle,
            description: this.givendescription,
            categoryId: this.selectedValue,
            closingDate: this.newdate}
        }

        this.$http.post('http://localhost:4941/api/v1/petitions', this.sql,{headers: {
            'X-Authorization': token}
        })

          .then((response) => {
            this.petition_id = response.data;
            this.SignPetition()
            console.log("Iam here")
            if(this.file != null){
              console.log("file is not null")
              this.setImage()
            }
            console.log(this.selectedValue)
            this.$router.push('/petitions')
            window.location.reload()
          })
          .catch((error) => {
            this.error = error;
            this.errorFlag = true;
          })
      },

      callDateFunction(){
          this.newdate = this.date.toISOString().split('T')[0]
      },

      SignPetition: function(){
        const token = localStorage.getItem('token')
        console.log('this.petition_id',this.petition_id.petitionId)
        this.$http.post('http://localhost:4941/api/v1/petitions/'+this.petition_id.petitionId +'/signatures', "",{headers: {
            'X-Authorization': token}
        })
      },

      setImage: function (){
        ///petitions/:id/photo'
        const token = localStorage.getItem('token')  ///petitions/:id/photo
        console.log('http://localhost:4941/api/v1/petitions/' + this.petition_id.petitionId + '/photo')
        console.log("the selected file is", this.file)
        this.axios.defaults.headers.common['X-Authorization'] = localStorage.getItem('token')
        this.axios.put('http://localhost:4941/api/v1/petitions/' + this.petition_id.petitionId + '/photo',this.file, {headers: {
          "Content-Type": this.file.type}
        }) . catch(function (error) {
          alert(error);

        })
      }










    }
  }


</script>

<style scoped>

</style>
