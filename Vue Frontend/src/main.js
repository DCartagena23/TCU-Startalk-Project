import { createApp } from 'vue'

import App from './App.vue'

import axios from 'axios'

// Importing Compiled CSS and JS from Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// Vue Router
import router from './router'

// Vuex
import store from './store'

//VeeValidate
import * as VeeValidate from 'vee-validate'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faVolumeUp } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
    faHome,
    faUser,
    faUserPlus,
    faSignInAlt,
    faSignOutAlt
  } from '@fortawesome/free-solid-svg-icons';
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);
import './assets/tailwind.css'

library.add(faVolumeUp)

const bootstrap = require('bootstrap')

const app = createApp(App)
app.component('font-awesome-icon', FontAwesomeIcon)

// In Vue 2, Vue.prototype was commonly used to add properties that would be accessible in all components.
// The equivalent in Vue 3 is config.globalProperties.
// To make axios and bootstrap object accessible in all Vue components, we add them to the // globalProperties.
app.config.globalProperties.$http = axios
app.config.globalProperties.$bootstrap = bootstrap

axios.defaults.baseURL = 'http://localhost:8081'

app.use(router)
app.use(store)
app.use(VeeValidate)
app.mount('#app')
