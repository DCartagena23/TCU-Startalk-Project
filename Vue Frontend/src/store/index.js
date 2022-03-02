import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import { auth } from './auth.module';

export default createStore({
  state: {

  },
  modules: {
    auth
  },
  mutations: {
  },
  plugins: [createPersistedState()],
})
