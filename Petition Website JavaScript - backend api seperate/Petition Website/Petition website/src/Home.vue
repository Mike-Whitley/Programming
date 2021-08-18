
<template>
    <dev> Hello
      <button v-on:click="MovePage">Hello There</button>
      <button v-on:click="MovePage2">Petitions</button>
      <p>test</p> <!---------this is an example of the global variable login status c an be called anywehre defined in main------->
    </dev>

</template>

<script>
  export default {
    data(){
      return{
        error: "",
        errorFlag: false,
      }
    },
    mounted: function() {
      this.getUsers();
    },
    methods: {

      MovePage: function(){
        this.$router.push('/users').catch((err) => {
        })
      },
      MovePage2: function(){
        this.$router.push('/petitions').catch((err) => {
        })
      }

    }
  }


</script>

<style scoped>

</style>
