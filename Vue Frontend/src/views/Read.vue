<template>
<Breadcrumb/>
  <div class="container">
    <!-- Text Area -->
    <div style="position: relative;" @mouseup="contextMenu($event)" id="element">
                
                <ul id="menu" class="container__menu container__menu--hidden" style="min-width: 55px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="translatePhrase()">
                  <img style="text-align: center; height:20px; width:20px" src="../assets/translate.png"/> </a></li> 
                <li><a class="dropdown-item" @click.prevent="tts()">
                  <img style="text-align: center; height:20px; width:20px" src="../assets/volume.png"/> </a></li> 
                </ul>

      <div :key="paragraph" v-for="paragraph in list">
        <div :key="word" v-for="word in paragraph">
          <table style="float: left; text-align: center">
            <tr style="text-align: center" class="noselect">
              <td v-if="flag" style="text-align: center; height: 30px"></td>
              <td v-else style="text-align: center; height: 30px" class="noselect" @click="showPinyinForm(word)">{{ word.pinyin }}</td>
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
    <button @click="toggle" class="btn btn-primary" style="margin-top: 10px">Pinyin</button>
    <button @click="edit()" class="btn btn-primary" style="margin-left: 10px; margin-top: 10px">Edit</button>
    <button @click="studentView()" class="btn btn-primary" style="margin-left: 10px; margin-top: 10px">Student View</button>
    <!-- Text Area -->

    <hr />

    <!-- Word List -->
    <div>
      <a class="btn btn-primary" @click.prevent="showNewForm">Add a New Word</a>
    </div>
    <hr />
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th style="width: 300px;">Grammar Word</th>
          <th>Description</th>
          <th style="width: 100px; text-align: center;">Operations</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="word.id" v-for="word in wordList">
          <td style="width: 300px;">{{ word.word }}</td>
          <td>{{ word.desc }}</td>
          <td style="width: 100px; text-align: center;">
            <div class="btn-group">
              <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                <img style="height:20px; width:20px" src="../assets/menu.png"/> 
              </button>
              <ul class="dropdown-menu" style="min-width: 55px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="showEditForm(word.id)">
                  <img style="text-align: center; height:20px; width:20px" src="../assets/gear.png"/> </a></li> 
                <li><a class="dropdown-item" @click.prevent="deletew(word.id)">
                  <img style="text-align: center; height:20px; width:20px" src="../assets/trash.png"/> </a></li> 
              </ul>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Word List -->

  <div class="modal fade" id="wordForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Highlight Color:</label>
              <select v-model="wordForm.color">
                <option disabled value="">Please select one</option>
                <option>Yellow</option>
                <option>Pink</option>
                <option>Green</option>
              </select>
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

  <div class="modal fade" id="pinyinForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ pinyinFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="word-name" class="col-form-label">Word:</label>
              <input type="text" class="form-control" id="id" v-model="pinyinForm.id" />
            </div>

            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Pinyin:</label>
              <input type="text" class="form-control" id="pinyin" v-model="pinyinForm.pinyin" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="savePinyin">Save</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="translateForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
</template>

<script>
// @ is an alias to /src
import Breadcrumb from '@/components/Breadcrumb.vue'
export default {
  name: 'Read',
  components: {
    Breadcrumb
  },
  data: function () {
    return {
      context: null,
      flag: true,
      list: [],
      chapter: {},
      wordList: [],
      pinyinList: [],

      wordForm: {},
      wordFormName: '',
      wordFormModal: null,

      pinyinForm: {},
      pinyinFormName: '',
      pinyinFormModal: null,

      translateForm: {},
      translateFormModal: null,
      translateFlag: true,

      menu: false,
      select:"",
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
          }
          this.pinyinList.forEach((pinyin) => {
            if (pinyin.id == json.word) {
              json.pinyin = pinyin.pinyin
            }
          })
          this.wordList.forEach((grammarWord) => {
            if (grammarWord.word == json.word) {
              json.id = grammarWord.id
              if (grammarWord.color == "Green") grammarWord.color = "#a8edb8"
              json.color = grammarWord.color
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
      //console.log(this.list)
    },  

    //get the grammar word list
    async getNewWordList(id) {
      const { data: res } = await this.$http.get(`/chapters/getGrammarWords/${id}`)
      this.wordList = res.data
      this.chapter.grammarWords = res.data
      this.setWordInfo()
    },

    //show add new grammar word form
    showNewForm() {
      this.translateFlag = true
      document.getElementById('word').disabled = false
      document.getElementById('desc').disabled = false
      this.wordFormName = 'Add a New Word'
      this.wordForm = {}
      this.wordFormModal.show()
    },

    //show edit grammar word form
    async showEditForm(id) {
      this.translateFlag = true
      document.getElementById('word').disabled = false
      document.getElementById('desc').disabled = false
      this.wordFormName = 'Edit a Word'
      const { data: res } = await this.$http.get(`/grammarWords/findOne/${id}`)
      this.wordForm = res.data
      this.wordFormModal.show()
    },

    //save or update grammar word
    async saveOrUpdate() {
      if (this.wordForm.id) {
        // update an existing word
        const { data: res } = await this.$http.put(`/grammarWords/updateGrammarWord/${this.chapter.id}`, this.wordForm)
        let index = this.wordList.findIndex((word) => word.id == res.data.id)
        this.wordList[index] = res.data
        this.getNewWordList(this.chapter.id)
      } else {
        // save a new word
        this.wordForm.id = this.objectId()
        this.wordForm.active = false
        const { data: res } = await this.$http.post(`/grammarWords/saveGrammarWord/${this.chapter.id}`, this.wordForm)
        this.word = [...this.wordList, res.data]
        this.getNewWordList(this.chapter.id)
      }
      this.wordFormModal.hide()
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
              if (grammarWord.color == "Green") grammarWord.color = "#a8edb8"
              word.color = grammarWord.color
            }
          }
        })
      })
    },


    //delete grammar word
    async deletew(id) {
      if (confirm('Do you want to delete this word?')) {
        const { data: res } = await this.$http.delete(`/grammarWords/deleteGrammarWord/${id}`)
        if (res.status == 200) {
          //delete Chapter with id from list
          //Step1, find index of the Chapter to be deleted
          let index = this.wordList.findIndex((word) => {
            return word.id == id
          })
          //Step2, delete this Chapter
          this.wordList.splice(index, 1)
          this.getNewWordList(this.chapter.id)
        } else {
          alert('Cannot delete Word!')
        }
      }
    },

    //show edit pinyin form
    showPinyinForm(word) {
      this.pinyinFormName = 'Edit Pinyin'
      this.pinyinForm = {}
      this.pinyinForm.id = word.word
      this.pinyinForm.pinyin = word.pinyin
      this.pinyinFormModal.show()
    },

    //save custom pinyin
    async savePinyin() {
      await this.$http.post(`/chapters/addPinyin/${this.chapter.id}`, this.pinyinForm)
      
      this.list.forEach((paragraph) => {
        paragraph.forEach((word) => {

          if (this.pinyinForm.id == word.word){
            word.pinyin = this.pinyinForm.pinyin
          }
        })
      })
      this.pinyinFormModal.hide()
    },

    //get pinyin using dictionary
    async getPinyinDict(json) {
      if (json.pinyin == '') {
        var chapter = {
          title: json.word,
        }
        const { data: res } = await this.$http.put(`/vocabWords/findOne`,chapter)
        if (res.status == 200) {
          json.pinyin = res.data.pinyin
        }
      }
    },

    edit() {
      this.$router.push({ path: `/edit/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.chapter.id}` })
    },

    studentView() {
      this.$router.push({ path: `/student/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.chapter.id}` })
    },

    //create new id
    objectId() {
      var timestamp = ((new Date().getTime() / 1000) | 0).toString(16)
      return (
        timestamp +
        'xxxxxxxxxxxxxxxx'
          .replace(/[x]/g, function () {
            return ((Math.random() * 16) | 0).toString(16)
          })
          .toLowerCase()
      )
    },

    toggle: function () {
      this.flag = !this.flag
    },

    //get highlight text
    checkSelectText() {
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

    saveSelectText() {
      if (window.getSelection) {
        var stringList = window.getSelection().toString().split('\n')
        var str = ''
        stringList.forEach((element) => {
          element = element.replace('\r', '')
          str = str + element
        })
        this.select = str;
      }
    },

    //read highlight text and output to tts
    async tts() {
      var str = this.select
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
      var str = this.select
      var chapter = {
        title: str,
      }
      this.translateForm.phrase = str
      document.getElementById('phrase').disabled = true
      document.getElementById('translate').disabled = true
      this.translateFormModal.show()

      const { data: res } = await this.$http.post(`/translate`, chapter)
      if (res.status == 200) {
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
    setHeader(){
      var current = new Date().getTime() / 1000
      if (current > this.$store.state.auth.expired){
        this.$store.dispatch('auth/logout')
        this.$router.push('/login');
      }
      else this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    },
    contextMenu(event) {
      var text = this.checkSelectText();
      if (text.length > 0 && !this.menu){
        this.saveSelectText();
        event.preventDefault();
        const menu = document.getElementById('menu');
        const ele = document.getElementById('element');
        const rect = ele.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY  - rect.top;

        // Set the position for menu
        menu.style.top = `${y}px`;
        menu.style.left = `${x}px`;

        menu.classList.remove('container__menu--hidden');
        this.menu = true;
      }
      else{
        document.getElementById("menu").className = "container__menu container__menu--hidden";
        this.menu = false;
      }
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
            .container {
                position: relative;
            }
            .container__trigger {
                /* Center the content */
                align-items: center;
                display: flex;
                justify-content: center;

                /* Size */
                height: 16rem;
                width: 16rem;

                /* Misc */
                border: 1px solid #cbd5e0;
            }
            .container__menu {
                /* Absolute position */
                position: absolute;

                /* Reset */
                list-style-type: none;
                margin: 0;
                padding: 0;

                /* Misc */
                border: 1px solid #cbd5e0;
                border-radius: 0.25rem;
                background-color: #f7fafc;
            }
            .container__menu--hidden {
                display: none;
            }
            .container__item {
                padding: 0.5rem 1rem;
                white-space: nowrap;
            }
            .container__item:hover {
                background-color: #bee3f8;
            }
            .container__divider {
                border-bottom: 1px solid #cbd5e0;
                height: 1px;
            }
</style>
