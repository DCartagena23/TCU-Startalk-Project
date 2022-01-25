import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  state: {
    bookId: '',
    chapterId: '',
    chapter: {},
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
