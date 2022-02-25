import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import { auth } from './auth.module';

export default createStore({
  state: {
    bookId: '',
    chapterId: '',
    chapter: {},
  },
  modules: {
    auth
  },
  mutations: {
    setId(state, { newId }) {
      state.bookId = newId
    },
    setChapterId(state, { newId }) {
      state.chapterId = newId
    },
    setChapter(state, { newChapter }) {
      state.chapter = newChapter
    },
  },
  plugins: [createPersistedState()],
})
