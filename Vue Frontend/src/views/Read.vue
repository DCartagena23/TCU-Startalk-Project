<template>
<div class="container">

  <!-- Text Area -->
      <div :key="paragraph" v-for="paragraph in list">

          <div :key="word" v-for="word in paragraph">
            <table v-if="word.timeStampHere" style = "float: left; text-align: center;margin-right:10px">
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;min-width:10px;">{{word.timeStampValue}}</td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;min-width:10px;" > <font-awesome-icon :icon="['fas', 'volume-up']" /> </td>
              </tr>
            </table>
            <table style = "float: left; text-align: center;">
              <tr style="text-align: center;" class="noselect">
                <td v-if="flag" style="text-align: center; height:30px;"></td>
                <td v-else style="text-align: center; height:30px;" class="noselect" @click="showPinyinForm(word)">{{word.pinyin}}</td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;" @dblclick="viewInfo(word.id)" v-bind:style="{ backgroundColor: word.color}">
                  {{word.word}}</td>
              </tr>
            </table>
            <table style = "float: left; text-align: center;">
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px; width:2px;"></td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px; width:2px;" ></td>
              </tr>
            </table>
          </div>

        <div style="clear:both;"></div>
      </div >
      <button @click="toggle" class="btn btn-primary" style="margin-top:10px;">Toggle</button>
      <button @click="tts()" class="btn btn-primary" style="margin-left: 10px; margin-top:10px;">TTS</button>
      <button @click="translatePhrase()" class="btn btn-primary" style="margin-left: 10px; margin-top:10px;">Translate</button>
      <button @click="edit()" class="btn btn-primary" style="margin-left: 10px; margin-top:10px;">Edit</button>
      <button @click="studentView()" class="btn btn-primary" style="margin-left: 10px; margin-top:10px;">Student View</button>
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
            <th>Grammar Word</th>
            <th>Description</th>
            <th>Operations</th>
          </tr>
        </thead>
        <tbody>
          <tr :key="word.id" v-for="word in wordList"  >
            <td>{{ word.word }}</td>
            <td>{{ word.desc }}</td>
            <td>
              <a class="btn btn-warning" style="margin-right:10px" @click.prevent="showEditForm(word.id)">Edit</a>
              <a class="btn btn-danger" style="margin-right:10px" @click.prevent="deletew(word.id)">Delete</a>
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
              <input type="text" class="form-control" id="word" v-model="wordForm.word"/>
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
              <input type="text" class="form-control" id="id" v-model="pinyinForm.id"/>
            </div>

             <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Pinyin:</label>
              <input type="text" class="form-control" id="pinyin" v-model="pinyinForm.pinyin"/>
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
          <h5 class="modal-title" id="exampleModalLabel">
            Translate
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="word-name" class="col-form-label">Phrase:</label>
              <input type="ph" class="form-control" id="phrase" v-model="translateForm.phrase"/>
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

export default {
  name: 'Read',
  data: function () {
    return {
      context: null,
      flag:true,
      list:[],
      chapter:{},
      wordList:[],
      pinyinList:[],

      wordForm: {},
      wordFormName: '',
      wordFormModal: null,

      pinyinForm: {},
      pinyinFormName: '',
      pinyinFormModal: null,
      
      translateForm: {},
      translateFormModal: null,
      translateFlag: true,
    }
  },  
    mounted: function () {
      this.init();
      console.log(this.$route.params.id)

      this.wordFormModal = new this.$bootstrap.Modal(document.getElementById('wordForm'), {})
      this.pinyinFormModal = new this.$bootstrap.Modal(document.getElementById('pinyinForm'), {})
      this.translateFormModal = new this.$bootstrap.Modal(document.getElementById('translateForm'), {})
  },
  created() {
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

    //get the grammar word list
    async getNewWordList(id) {
      const { data: res } = await this.$http.get(`/chapters/getGrammarWords/${id}`)
      this.wordList = res.data
      this.chapter.grammarWords = res.data
      this.storeChapter()
      this.setWordInfo()
    },

    //show add new grammar word form
        showNewForm() {
          this.translateFlag = true
      document.getElementById('word').disabled = false;
      document.getElementById('desc').disabled = false;
      this.wordFormName = 'Add a New Word'
      this.wordForm = {}
      this.wordFormModal.show()
    },

    //show edit grammar word form
    async showEditForm(id) {
      this.translateFlag = true
      document.getElementById('word').disabled = false;
      document.getElementById('desc').disabled = false;
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
        let index = this.wordList.findIndex((word) =>word.id == res.data.id)
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
      document.getElementById('word').disabled = true;
      document.getElementById('desc').disabled = true;
      this.wordFormModal.show()
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

    //get the custom pinyin list
    async getPinyinList(id) {
      const { data: res } = await this.$http.get(`/chapters/getPinyin/${id}`)
      this.pinyinList = res.data
      this.chapter.pinyin = res.data
      this.storeChapter()
      this.setPinyinInfo()
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
        await this.$http.put(`/pinyins/updatePinyin/${this.chapter.id}`, this.pinyinForm)
        this.getPinyinList(this.chapter.id)
      this.pinyinFormModal.hide()
    },

    //get pinyin using dictionary
      async getPinyinDict(json) {
        if (json.pinyin == ""){
          const { data: res } = await this.$http.get(`/dictionary/pinyin/${json.word}`)
          if (res.status == 200) {
            json.pinyin = res.data
          }
        } 
      },

    //set pinyin to word
    setPinyinInfo(){
      this.list.forEach(paragraph => {
        paragraph.forEach (word => {
          for (var m=0; m<this.pinyinList.length; m++){
              var pinyin = this.pinyinList[m]
              if (word.word == pinyin.id){
                word.pinyin = pinyin.pinyin
              }
            }
        })
      })
    },

    //set grammar word to word
     setWordInfo(){
      this.list.forEach(paragraph => {
        paragraph.forEach (word => {
        word.id = -1
            word.color = "white"
            for (var m=0; m<this.wordList.length; m++){
              var grammarWord = this.wordList[m]
              if (word.word == grammarWord.word){
                word.id = grammarWord.id
                word.color = "yellow"
              }
            }
        })
      })
    },

    //read the chapter.text and display into reading area, embedded with pinyin and meaning if available
    showText(){
        this.list = []
        var paragraph = []
        var currentIndex = 0
        var timeIndex =0
        var timeList = this.chapter.timeStamp
        this.chapter.text.forEach((element, index) => {
          if (element == "\n"){
            this.list.push(paragraph)
            paragraph = []
            currentIndex = currentIndex+1
          }
          else {
            var json ={
              "id": -1,
              "word": element,
              "color": "white",
              "pinyin": "",
              "timeStampHere": false,
              "timeStampValue": ""
            }
            this.pinyinList.forEach(pinyin => {
              if (pinyin.id == json.word){
                json.pinyin = pinyin.pinyin
              }
            })
            this.wordList.forEach(grammarWord => {
              if (grammarWord.word == json.word){
                json.id = grammarWord.id
                json.color = "yellow"
              }
            })
            if (timeList[timeIndex] != undefined){
              if (currentIndex == timeList[timeIndex].location){
                json.timeStampHere = true
                json.timeStampValue = timeList[timeIndex].time
                timeIndex = timeIndex +1
              }
            }
            currentIndex = currentIndex +1
            this.getPinyinDict(json)
            paragraph.push(json)
          }
          if (index == this.chapter.text.length-1){
            this.list.push(paragraph)
          }
        })
    },

    edit() {
      this.storeChapter()
      this.$router.push({ name: 'Edit'})
    },

    studentView() {
      this.storeChapter()
      this.$router.push({ name: 'Student'})
    },

    //create new id
    objectId() {
      var timestamp = ((new Date().getTime() / 1000) | 0).toString(16);
      return (
        timestamp +
        "xxxxxxxxxxxxxxxx"
          .replace(/[x]/g, function() {
            return ((Math.random() * 16) | 0).toString(16);
          })
          .toLowerCase()
      );
    },

    toggle: function() {
          this.flag = !this.flag
        },


    storeChapter(){
      this.$store.commit('setChapter',{newChapter : this.chapter})
    },


    //get highlight text
    getSelectText(){
      if (window.getSelection){
       var stringList = window.getSelection().toString().split("\n")
       var str =""
       stringList.forEach(element => {
         element = element.replace("\r", "")
         str = str + element
       })
       return str
      }
    },

    //read highlight text and output to tts
    async tts(){
      var str = this.getSelectText()
      var chapter = {
        "title": str
      }
      const { data: res } = await this.$http.post(`/tts`, chapter)
      if (res.status == 200) {
        var audio = new Audio("data:audio/mp3;base64," + res.data);
        audio.play();
      }
    },

    //read highlight text and translate
    async translatePhrase() {
      var str = this.getSelectText()
      var chapter = {
        "title": str
      }
      this.translateForm.phrase = str
      document.getElementById('phrase').disabled = true;
      document.getElementById('translate').disabled = true;
      this.translateFormModal.show()  
      
      const { data: res } = await this.$http.post(`/translate`, chapter)
      if (res.status == 200) {
        console.log(res.data)
        this.translateForm.translate = res.data
      }
    },


    //init audio
    init(){
      try {
        // Fix up for prefixing
        window.AudioContext = window.AudioContext||window.webkitAudioContext;
        this.context = new AudioContext();
      }
      catch(e) {
        alert('Web Audio API is not supported in this browser');
      }
    },

    //translate
    async translateWord(){
      var chapter = {
        "title": this.wordForm.word
      }
      const { data: res } = await this.$http.post(`/translate`, chapter)
      if (res.status == 200) {
        this.wordForm.desc = res.data
      }
    },



    // async getWord(id) {
    // try{
    //   const { data: res } = await this.$http.get(`/words/findOne/${id}`)
    //   if (res.status == 200) {
    //       this.wordList.push(res.data)
    //   }
    // } catch (error){
    //     this.createWord(id)
    // }
    // },
    
    // async createWord(id) {
    // const { data: res } = await this.$http.get(`/words/create/${id}`)
    // if (res.status == 200) {
    //   this.wordList.push(res.data)
    // }
    // },  
    // getList(){
    //     var paragraph = this.chapter.text.split("\n")
    //     for (var j = 0; j<paragraph.length;j++){
    //       var word = paragraph[j].split("")
    //       for (var i = 0; i<word.length; i++) {
    //         this.getWord(word[i])
    //       }
    //   }
    // }
  }
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
