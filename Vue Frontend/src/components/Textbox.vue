<template>
  <div >
    <div id="textbook">
      <div>{{chapter.title}}</div>
      <div id="textbox">
        <div :key="paragraph" v-for="paragraph in list">
          <div :key="word" v-for="word in paragraph">
            <table v-if="word.timeStampHere" style="float: left; text-align: center; margin-right: 10px">
              <tr style="text-align: center">
                <td style="text-align: center; height: 30px; min-width: 10px">{{ word.timeStampValue }}</td>
              </tr>
              <tr style="text-align: center">
                <td style="text-align: center; height: 30px; min-width: 10px"><font-awesome-icon :icon="['fas', 'volume-up']" /></td>
              </tr>
            </table>
            <table style="float: left; text-align: center">
              <tr style="text-align: center" class="noselect">
                <td v-if="flag" style="text-align: center; height: 30px"></td>
                <td v-else style="text-align: center; height: 30px" class="noselect">{{ word.pinyin }}</td>
              </tr>
              <tr style="text-align: center">
                <td style="text-align: center; height: 30px" @dblclick="viewInfo(word.id)" v-bind:style="{ backgroundColor: word.color }">
                  {{ word.word }}
                </td>
              </tr>
            </table>
            <table style="float: left; text-align: center">
              <tr style="text-align: center">
                <td style="text-align: center; height: 30px; width: 2px"></td>
              </tr>
              <tr style="text-align: center">
                <td style="text-align: center; height: 30px; width: 2px"></td>
              </tr>
            </table>
          </div>
          <div style="clear: both"></div>
        </div>
      </div>
    </div>
    <button type="button" :disabled=previousDisable @click="previous()" class=" btn btn-primary inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="goPrevPage" style="float: left; margin: 5px 5px 5px 0">Previous Chapter</button>
    <button type="button" v-if=checkRole() @click="edit()" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 0 0 0">Edit Passage</button>
    <button type="button" :disabled=nextDisable @click="next()" class="btn btn-primary inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="goNextPage" style="float: right; margin: 5px 0 5px 5px">Next Chapter</button>

    <nav class="navbar fixed-bottom navbar-expand-lg navbar-dark bg-dark" style="text-align: center">
      <div class="mx-auto">
        <button type="button" @click="toggle" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin-top: 10px">Toggle</button>
        <button type="button" @click="tts()" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin-left: 10px; margin-top: 10px">TTS</button>
        <button type="button" @click="translatePhrase()" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin-left: 10px; margin-top: 10px">Translate</button>
      </div>
    </nav>

    <div class="modal fade left" id="wordForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              {{ wordFormName }}
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="word-name" class="col-form-label">Word:</label>
                <input type="text" class="form-control" id="word" v-model="wordForm.word" />
              </div>

              <div class="mb-3">
                <label for="chapter-name" class="col-form-label">Desc:</label>
                <textarea class="form-control" id="desc" rows="4" v-model="wordForm.desc"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" v-if="translateFlag" class="btn btn-primary" @click="translateWord">Translate</button>
            <button type="button" class="btn btn-primary" @click="saveOrUpdate">Save</button>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade left" id="translateForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Translate</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="word-name" class="col-form-label">Phrase:</label>
                <input type="ph" class="form-control" id="phrase" v-model="translateForm.phrase" />
              </div>

              <div class="mb-3">
                <label for="chapter-name" class="col-form-label">Translate:</label>
                <textarea class="form-control" id="translate" rows="4" v-model="translateForm.translate"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#textbook {
  padding: 25px;
  /* margin:25px; */
  text-align: center;
  border-width: 2px;
  border-style: solid;
}
</style>

<script>
export default {
  name: 'Textbox',
  props: {
    toggleEditButton: Boolean,
    toggleEditMode: Function,
    textbox: String,
    goPrevPage: Function,
    goNextPage: Function,
  },
  data: function () {
    return {
      context: null,
      flag: true,
      list: [],
      chapter: {},
      wordList: [],
      pinyinList: [],
      chapList:[],

      wordForm: {},
      wordFormName: '',
      wordFormModal: null,

      pinyinForm: {},
      pinyinFormName: '',
      pinyinFormModal: null,

      translateForm: {},
      translateFormModal: null,
      translateFlag: true,

      previousDisable: false,
      nextDisable: false,
      previousId: "",
      nextId:""
    }
  },
  mounted: function () {
    this.init()

    this.wordFormModal = new this.$bootstrap.Modal(document.getElementById('wordForm'), {})
    this.pinyinFormModal = new this.$bootstrap.Modal(document.getElementById('pinyinForm'), {})
    this.translateFormModal = new this.$bootstrap.Modal(document.getElementById('translateForm'), {})
  },
  created() {
    this.setHeader()
    this.getChapter(this.$route.params.id)
    this.getChapList(this.$route.params.bookId)
  },
  methods: {
    //get the chapter using id
    async getChapter(id) {
      const { data: res } = await this.$http.get(`/chapters/findOne/${id}`)
      if (res.status == 200) {
        this.chapter = res.data

        this.wordList = this.chapter.grammarWords
        this.pinyinList = this.chapter.pinyin
        this.showText()
      }
    },

      //read the chapter.text and display into reading area, embedded with pinyin and meaning if available
    async showText() {
      this.list = []
      var paragraph = []
      var currentIndex = 0
      // var timeIndex = 0
      // var timeList = this.chapter.timeStamp
      this.chapter.text.forEach((element, index) => {
        if (element == '\n') {
          this.list.push(paragraph)
          paragraph = []
          currentIndex = currentIndex + 1
        } else {  
          var json = {
            id: -1,
            word: element,
            color: 'white',
            pinyin: '',
            timeStampHere: false,
            timeStampValue: '',
          }
          this.pinyinList.forEach((pinyin) => {
            if (pinyin.id == json.word) {
              json.pinyin = pinyin.pinyin
            }
          })
          this.wordList.forEach((grammarWord) => {
            if (grammarWord.word == json.word) {
              json.id = grammarWord.id
              json.color = 'yellow'
            }
          })
          if (json.pinyin == "") {
            this.getPinyinDict(json)
          }
          // if (timeList[timeIndex] != undefined) {
          //   if (currentIndex == timeList[timeIndex].location) {
          //     json.timeStampHere = true
          //     json.timeStampValue = timeList[timeIndex].time
          //     timeIndex = timeIndex + 1
          //   }
          // }
          // currentIndex = currentIndex + 1

          // this.getPinyinDict(json)
          paragraph.push(json)
        }
        if (index == this.chapter.text.length - 1) {
          this.list.push(paragraph)
        }
      })
    },  

    //get the grammar word list
    async getNewWordList(id) {
      const { data: res } = await this.$http.get(`/chapters/getGrammarWords/${id}`)
      this.wordList = res.data
      this.chapter.grammarWords = res.data
      this.setWordInfo()
    },

    //view grammar word info
    async viewInfo(id) {
      if (id == -1) return
      this.wordFormName = 'Word Info'
      const { data: res } = await this.$http.get(`/grammarWords/findOne/${id}`)
      this.wordForm = res.data
      this.translateFlag = false
      document.getElementById('word').disabled = true
      document.getElementById('desc').disabled = true
      this.wordFormModal.show()
    },

    //set grammar word to word
    setWordInfo() {
      this.list.forEach((paragraph) => {
        paragraph.forEach((word) => {
          word.id = -1
          word.color = 'white'
          for (var m = 0; m < this.wordList.length; m++) {
            var grammarWord = this.wordList[m]
            if (word.word == grammarWord.word) {
              word.id = grammarWord.id
              word.color = 'yellow'
            }
          }
        })
      })
    },

    //get pinyin using dictionary
    async getPinyinDict(json) {
      if (json.pinyin == '') {
        var chapter = {
          title: json.word,
        }
        console.log(chapter)
        const { data: res } = await this.$http.put(`/vocabWords/findOne`,chapter)
        if (res.status == 200) {
          json.pinyin = res.data.pinyin
        }
      }
    },

    edit() {
      this.$router.push({ path: `/read/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.chapter.id}` })
    },

    toggle: function () {
      this.flag = !this.flag
    },

    //get highlight text
    getSelectText() {
      if (window.getSelection) {
        var stringList = window.getSelection().toString().split('\n')
        var str = ''
        stringList.forEach((element) => {
          element = element.replace('\r', '')
          str = str + element
        })
        return str
      }
    },

    //read highlight text and output to tts
    async tts() {
      var str = this.getSelectText()
      var chapter = {
        title: str,
      }
      const { data: res } = await this.$http.post(`/tts`, chapter)
      if (res.status == 200) {
        var audio = new Audio('data:audio/mp3;base64,' + res.data)
        audio.play()
      }
    },

    //read highlight text and translate
    async translatePhrase() {
      var str = this.getSelectText()
      var chapter = {
        title: str,
      }
      this.translateForm.phrase = str
      document.getElementById('phrase').disabled = true
      document.getElementById('translate').disabled = true
      this.translateFormModal.show()

      const { data: res } = await this.$http.post(`/translate`, chapter)
      if (res.status == 200) {
        console.log(res.data)
        this.translateForm.translate = res.data
      }
    },

    //init audio
    init() {
      try {
        // Fix up for prefixing
        window.AudioContext = window.AudioContext || window.webkitAudioContext
        this.context = new AudioContext()
      } catch (e) {
        alert('Web Audio API is not supported in this browser')
      }
    },

    //translate
    async translateWord() {
      var chapter = {
        title: this.wordForm.word,
      }
      const { data: res } = await this.$http.post(`/translate`, chapter)
      if (res.status == 200) {
        this.wordForm.desc = res.data
      }
    },

    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },

    async getChapList(id) {
      const { data: res } = await this.$http.get(`/books/getChapters/${id}`)
      if (res.status == 200){
        this.chapList = res.data
        this.sorted()
        let index = this.chapList.findIndex((chapter) => chapter.id == this.$route.params.id)
        if (index == 0) this.previousDisable = true
        else {
          this.previousId = this.chapList[index-1].id
          this.previousDisable = false
        }
        if (index == this.chapList.length - 1) this.nextDisable = true
        else {
          this.nextId = this.chapList[index+1].id
          this.nextDisable = false
        }
      }

    },

    previous(){
      this.$router.replace({ path: `/student/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.previousId}` })
      this.setHeader()
      this.getChapter(this.previousId)
      this.getChapList(this.$route.params.bookId)

    },

    next(){
      this.$router.replace({ path: `/student/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.nextId}` })
      this.setHeader()
      this.getChapter(this.nextId)
      this.getChapList(this.$route.params.bookId)

    },

        setHeader(){
      var current = new Date().getTime() / 1000
      if (current > this.$store.state.auth.expired){
        this.$store.dispatch('auth/logout')
        this.$router.push('/login');
      }
      else this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    },

    sorted() {
      return this.chapList.sort((a, b) => {
        return a.number - b.number
      })
    },
  },
}
</script>
<style>
.noselect {
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -khtml-user-select: none; /* Konqueror HTML */
  -moz-user-select: none; /* Old versions of Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently
                                  supported by Chrome, Edge, Opera and Firefox */
}
</style>
