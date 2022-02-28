<template>
  <div class="container">
    <!-- Audio Answer List -->
    <h1>{{test.title}}: Answer List</h1>
    <hr />
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>User</th>
          <th>Audio</th>
          <th>Grade</th>
          <th>Operations</th>
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
          <td>
            <a class="btn btn-success" v-if=checkRole() style="margin-right: 10px" @click.prevent="showGradeForm(answer.id)">Grade</a>
            <a class="btn btn-success" @click.prevent="viewComment(answer.id)">View Comment</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

    <div class="modal fade" id="gradeForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ gradeFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Test Name: {{test.title}}</label>
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">User: {{gradeForm.user}}</label>
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Grade:</label>
              <input type="text" class="form-control" v-model="gradeForm.grade" />
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Comment:</label>
              <textarea class="form-control" id="newText" rows="4" v-model="newText"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="changeGrade">Save</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'AudioTest',

  data: function () {
    return {
      test: {},
      answerList: [],
      newText:"",
      
      gradeForm: {},
      gradeFormName: '',
      gradeFormModal: null,
    }
  },
  mounted: function () {
    this.gradeFormModal = new this.$bootstrap.Modal(document.getElementById('gradeForm'), {})
  },
  created() {
    this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    this.getTest(this.$route.params.id)
    this.getAnswerList(this.$route.params.id)
  },
  methods: {
        async getTest(id) {
            const { data: res } = await this.$http.get(`/audioTests/findOne/${id}`)
            this.test = res.data
            this.countDown = this.test.prepTime
            console.log(this.test)
        },

        async getAnswerList(id) {
            const { data: res } = await this.$http.get(`/storage/getAudioAnswerList/${id}`)
            this.answerList = res.data
            console.log(this.answerList)
        },

    async showGradeForm(id) {
      this.gradeFormName = 'Change Grade'
      const { data: res } = await this.$http.get(`/audioTests/getAnswer/${this.$route.params.id}/${id}`)
      this.gradeForm = res.data
      this.gradeFormModal.show()
    },

        async changeGrade() {   
          await this.$http.put(`/audioTests/changeGrade/${this.$route.params.id}/${this.gradeForm.id}`, this.gradeForm)
          let index = this.answerList.findIndex((answer) => answer.id == this.gradeForm.id)
          this.answerList[index].grade = this.gradeForm.grade
          this.addComment(this.gradeForm.id)

          this.gradeFormModal.hide()
        },

      async addComment(id){
        var json = {
        id: this.objectId(),
        user: this.$store.state.auth.user.username,
        content: this.newText,
        instructor: this.checkRole()
        }
        await this.$http.put(`/audioTests/addComment/${this.$route.params.id}/${id}`, json)
        this.newText = ""
      },
    
    viewComment(id){
      this.$router.push({ path: `/audioAnswerComment/${this.$route.params.id}/${id}` })
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
  },
}
</script>

<style scoped></style>
