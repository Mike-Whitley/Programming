import Vue from 'vue'
import App from './App.vue'
import Home from './Home.vue'
import Users from './Users.vue'
import Petitions from './Petitions.vue'
import Register from './Register.vue'
import CreatePetition from './CreatePetition.vue'
import Signatures from './Signatures.vue'
import Login from './Login.vue'
import IndividualPetitions from './IndividualPetitions.vue'
import axios from 'axios';
import VueAxios from "vue-axios";
import VueRouter from 'vue-router';
import {BootstrapVue} from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import Viewprofile from "./Viewprofile";

// global variable
Vue.prototype.$loginstatus = false       //this is a global variable


Vue.use(VueAxios, axios)
Vue.use(VueRouter);
Vue.use(BootstrapVue)

const routes = [
  {
    path: "/", //make petitions home page
    component: Petitions
  },
  {
    path: "/users/:userId", //this is the URL
    name: "user", // this is the name we use to call it inside the function
    component: Users //this is the Vue file name
  },
  {
    path: "/users",
    name:'users', // this is the name when we say <router-link :to="{name: 'users'}">Back to Users</router-link> that we return to
    component: Users
  },
  {
    path: "/petitions",
    name:'petitions',
    component: Petitions,
    props: true
  },
  {
    path: "/individual/:petitionId",
    name:'individualpetitions',
    component: IndividualPetitions
  },
  {
    path: "/signatures",
    name:'signatures',
    component: Signatures
  },
  {
    path: "/register",
    name:'Register',
    component: Register
  },
  {
    path: "/login",
    name:'login',
    component: Login
  },
  {
    path: "/createpetition",
    name:'createpetition',
    component: CreatePetition
  },
  {
    path: "/viewprofile",
    name:'viewprofile',
    component: Viewprofile
  },

];

const router = new VueRouter({
  routes: routes,
  mode: 'history'
})


new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
})
