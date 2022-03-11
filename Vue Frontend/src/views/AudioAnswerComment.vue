<template>
<Breadcrumb/>
  <div class="container">
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
import Breadcrumb from '../components/Breadcrumb.vue'
export default {
  components: { Breadcrumb },
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
    this.setHeader()
    this.getTest(this.$route.params.id)
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
            const { data: res } = await this.$http.get(`/storage/getAudioAnswer/${this.$route.params.id}/${this.$route.params.answerId}`)
            this.answerList.push(res.data)
            this.commentList = res.data.comments
        },

        async addComment(){
            var json = {
            id: this.objectId(),
            user: this.$store.state.auth.user.username,
            content: this.newText,
            instructor: this.checkRole()
            }
            const { data: res } = await this.$http.put(`/audioTests/addComment/${this.$route.params.id}/${this.$route.params.answerId}`, json)
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

    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },
        setHeader(){
      var current = new Date().getTime() / 1000
      if (current > this.$store.state.auth.expired){
        this.$store.dispatch('auth/logout')
        this.$router.push('/login');
      }
      else this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    },
  },
}
</script>

<style scoped></style>
