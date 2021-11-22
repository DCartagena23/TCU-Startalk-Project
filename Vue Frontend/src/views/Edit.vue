<template>
<div class="container">  
  <!-- Text Area -->
    <div v-if="flag" class="form-group">
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" v-model="newText"></textarea>
    <a class="btn btn-primary" @click.prevent="changeTextList">Change Text</a>
    <a class="btn btn-primary" style="margin-left: 10px;" @click.prevent="showText">Skip Change Text</a>
    </div>
    <div v-else>
        <div :key="paragraph" v-for="paragraph in list">

          <div :key="word" v-for="word in paragraph">
            <table v-if="word.timeStampHere" style = "float: left; text-align: center;" >
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;min-width:10px;">
                  <input type="text" style="width:50px;text-align: center;padding: 0 0 0 0" 
                  class="form-control"  v-model="word.timeStampValue"/>
                  </td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;min-width:10px; " ><font-awesome-icon :icon="['fas', 'volume-up']" /></td>
              </tr>
            </table>

            <table @click.prevent="toggleSpace(word)" @click.right.prevent="toggleTimeStamp(word)" style = "float: left; text-align: center;">
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;"></td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px;" >{{word.word}}</td>
              </tr>
            </table>
            <table v-if="word.space" @click.prevent="toggleSpace(word)" style = "float: left; text-align: center;">
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px; width:15px;"></td>
              </tr>
              <tr style="text-align: center;">
                <td style="text-align: center; height:30px; width:15px;" ></td>
              </tr>
            </table>
          </div>

        <div style="clear:both;"></div>
      </div>
      <a class="btn btn-primary" @click.prevent="tokenize">Tokenize</a>
      <a class="btn btn-primary" style="margin-left: 10px;" @click.prevent="save">Save Text</a>
      <a class="btn btn-secondary" style="margin-left: 10px;" @click.prevent="toggle">Back</a>
    </div>
   <!-- Text Area --> 
</div>

</template>


<script>
// @ is an alias to /src

export default {
  name: 'Edit',
  data: function () {
    return {
      flag:true,
      chapter:{},
      list:[],
      newText:"",
      timeList:[],
    }
  },  
  mounted: function() {
      this.chapter = this.$store.state.chapter
      this.setText()
  },  
  created() {

  },
  methods:{
      setText(){
        this.newText = "";
        this.chapter.text.forEach(element => {
          if (element == "\n") this.newText = this.newText + element
          else this.newText = this.newText + element +" "
        })
      },

      changeTextList(){
        var newList = []
        var paragraph = this.newText.split("\n")
        paragraph.forEach((element,index1) => {
          var wordList = element.split(" ")
          wordList.forEach((word,index2) => {
            if (word != "" && word != " ") newList.push(word)
            if (index2 == wordList.length-1 && index1 != paragraph.length-1) newList.push("\n")
          })
        })
        this.chapter.text = newList
        this.showText()
      },

      showText(){
        this.list =[]
        var paragraph = []
        this.chapter.text.forEach((element,index1) => {
          if (element == "\n"){
            this.list.push(paragraph)
            paragraph =[]
          }
          else {
            var characterList = element.split("")
            characterList.forEach((character,index2) => {
              var json ={
                "word": character,
                "space": false,
                "timeStampHere": false,
                "timeStampValue": ""
              }
              if (index2 == characterList.length -1) json.space = true
              paragraph.push(json)
            })
          }
          if (index1 == this.chapter.text.length-1){
            this.list.push(paragraph)
            paragraph =[]
          }
        });
        this.flag = false
        console.log(this.list)
      },

      async tokenize() {
        const { data: res } = await this.$http.post(`/tokenize`,this.chapter)
        if (res.status == 200) {
          this.chapter.text = res.data
        }
        this.showText()
        console.log("Tokenize Success")
      },

      toggleSpace(word){
        word.space = !word.space
      },

      toggleTimeStamp(word){
        word.timeStampHere = !word.timeStampHere
      },

      toggle(){
        this.flag = !this.flag
      },
      save(){
        var textList = []
        var timeList = []
        this.list.forEach((paragraph,index1) => {
          var word = ""
          var timeStampHere = false
          var timeStampValue = ""
          paragraph.forEach((character,index2) => {
            if (character.timeStampHere == true) {
              timeStampHere = true
              timeStampValue = character.timeStampValue
            }
            word = word + character.word
            if (character.space == true){
              textList.push(word)
              if (timeStampHere){
                var json = {
                  "id": this.objectId(),
                  "time": timeStampValue,
                  "location": textList.length-1
                }
                timeList.push(json)
              }
              word = ""
              timeStampHere = false
              timeStampValue = ""
            }
            if (index2 == paragraph.length-1 && index1 != this.list.length-1){
              textList.push("\n")
            }
          })
        })  
        this.chapter.text = textList
        this.chapter.timeStamp = timeList
        this.saveChapter()
      },

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

      async saveChapter(){
        await this.$http.put(`/chapters/updateChapter/${this.$store.state.bookId}`, this.chapter)
        this.$store.commit('setChapter',{newChapter : this.chapter})
        this.$router.push({ name: 'Read'})
      },
  }
}
</script>

