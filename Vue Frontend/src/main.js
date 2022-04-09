
import { createApp } from 'vue'

import App from './App.vue'

import axios from 'axios'

// Importing Compiled CSS and JS from Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'bootstrap/dist/js/bootstrap.bundle.js' 

// Vue Router
import router from './router'

// Vuex
import store from './store'

//VeeValidate
import * as VeeValidate from 'vee-validate'

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import {
    faHome,
    faUser,
    faUserPlus,
    faSignInAlt,
    faSignOutAlt
  } from '@fortawesome/free-solid-svg-icons';
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);
import './assets/tailwind.css'

library.add(fas  )

const bootstrap = require('bootstrap')

const app = createApp(App)
app.component('font-awesome-icon', FontAwesomeIcon)

// In Vue 2, Vue.prototype was commonly used to add properties that would be accessible in all components.
// The equivalent in Vue 3 is config.globalProperties.
// To make axios and bootstrap object accessible in all Vue components, we add them to the // globalProperties.
app.config.globalProperties.$http = axios
app.config.globalProperties.$bootstrap = bootstrap

//axios.defaults.baseURL = 'http://localhost:8081'
axios.defaults.baseURL = 'https://tcu-cnn22.herokuapp.com/'

router.beforeEach((to) => {
  if (
    // make sure the user is authenticated
    !store.state.auth.status.loggedIn &&
    // ❗️ Avoid an infinite redirect
    to.name !== 'Login' && to.name !== 'Register'
  ) {
    // redirect the user to the login page
    return { name: 'Login' }
  }
})

app.use(router)
app.use(store)
app.use(VeeValidate)
app.mount('#app')
