<template>
  <div class="container">
    <input type="text" class="form-control" v-model="user" />
    <h6 class="card-title" style="font-size: 2em">{{test.title}}</h6>
    <hr />
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>User</th>
          <th>Audio</th>
          <th>Grade</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="answer.id" v-for="answer in answerList">
          <td>{{ answer.user }}</td>
          <td>        
              <audio controls style="width: 95%; margin: 5px">
                <source :src="answer.key" type="audio/wav">
              </audio>
          </td>
          <td>{{ answer.grade }}</td>
        </tr>
      </tbody>
    </table>
    <hr />

    <h6 class="card-title" style="font-size: 2em">Comment</h6>
    <table class="table table-striped table-hover" style="border: 1px solid black">
      <thead>
        <tr>
          <th>User</th>
          <th>Content</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="comment.id" v-for="comment in commentList">
          <td>{{ comment.user }}</td>
          <td v-bind:style="{ backgroundColor: this.getColor(comment) }">{{ comment.content }}</td>
        </tr>
      </tbody>
    </table>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="6" v-model="newText"></textarea>
    <a class="btn btn-primary" @click.prevent="addComment">Post Comment</a>
  </div>
</template>

<script>
// @ is an alias to /src    

export default {
  name: 'AudioAnswerComment',

  data: function () {
    return {
      test: {},
      answerList: [],
      commentList: [],
      newText: ``,
      user: "NhanLy"
      
    }
  },
  mounted: function () {
  },
  created() {
    this.getTest(this.$route.params.testId)
    this.getAnswer()
  },
  methods: {
        async getTest(id) {
            const { data: res } = await this.$http.get(`/audioTests/findOne/${id}`)
            this.test = res.data
            this.countDown = this.test.prepTime
            console.log(this.test)
        },  

        async getAnswer(){
            const { data: res } = await this.$http.get(`/storage/getAudioAnswer/${this.$route.params.testId}/${this.$route.params.answerId}`)
            this.answerList.push(res.data)
            this.commentList = res.data.comments
        },

        async addComment(){
            var json = {
            id: this.objectId(),
            user: this.user,
            content: this.newText,
            instructor: false
            }
            const { data: res } = await this.$http.put(`/audioTests/addComment/${this.$route.params.testId}/${this.$route.params.answerId}`, json)
            this.commentList = [...this.commentList, res.data]
            this.newText = ""
        },

        getColor(comment){
            if (comment.instructor == true) return "yellow"
            else return "white"
        },

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
  },
}
</script>

<style scoped></style>
