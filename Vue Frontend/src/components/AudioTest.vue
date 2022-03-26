<template>
<div>
  <div class="container">
    <!-- Audio Test List -->
    <div class="py-6">
    <h1 class="display-6" style="text-align:center;">Audio Test List</h1>
    <div style="text-align: center;">
      <!-- <a class="btn btn-primary" v-if=checkRole() @click.prevent="showNewTestForm">Create a new Audio Test</a> -->
       <button type="button" class="px-6
       py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;margin-right: 10px;"
      v-if=checkRole()
      @click.prevent="showNewTestForm"
      >
      Create a New Audio Test
      </button>
    </div>
    </div>
    <!-- <hr /> -->
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Prepare Time (seconds)</th>
          <th>Operations</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="test.id" v-for="test in testList">
          <td v-if="test.active">{{ test.id }}</td>
          <td v-if="test.active">{{ test.title }}</td>
          <td v-if="test.active">{{ test.prepTime }}</td>
          <td v-if="test.active">
            <!-- <a class="btn btn-success" style="margin-right: 10px" @click.prevent="startTest(test.id)">Start Test</a> -->
             <button type="button" class="px-6
       py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;margin-right: 10px;"
      @click.prevent="startTest(test)"
      >
      Start Test
            </button>
            <!-- <a class="btn btn-warning" v-if=checkRole() style="margin-right: 10px" @click.prevent="showEditTestForm(test.id)">Edit</a> -->
              <button type="button" class="px-6
       py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;margin-right: 10px;"
      @click.prevent="showEditTestForm(test)"
      v-if=checkRole()
      >
      Edit
            </button>
            <!-- <a class="btn btn-danger"  v-if=checkRole() style="margin-right: 10px" @click.prevent="deleteTest(test.id)">Delete</a> -->
            <button type="button" class="px-6
       py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;margin-right: 10px;"
      @click.prevent="deleteTest(test.id)"
      v-if=checkRole()
      >
      Delete
            </button>
            <!-- <a class="btn btn-success" style="margin-right: 10px" @click.prevent="viewAnswer(test.id)">View Submitted Answer</a> -->
            <button type="button" class="px-6
       py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;margin-right: 10px;"
      @click.prevent="viewAnswer(test.id)"
      >
      View Submitted Answer
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="modal fade" id="testForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ testFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Test Name:</label>
              <input type="text" class="form-control" v-model="testForm.title" />
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Prepare Time:</label>
              <input type="text" class="form-control" v-model="testForm.prepTime" />
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Speaking Prompt:</label>
              <textarea class="form-control" rows="4" v-model="testForm.prompt"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="saveOrUpdateTest">Save</button>
        </div>
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
      testList: [],
      book: {},
      chapter: {},

      testForm: {},
      testFormName: '',
      testFormModal: null,
    }
  },
  mounted: function () {
    this.testFormModal = new this.$bootstrap.Modal(document.getElementById('testForm'), {})
  },
  created() {
    this.setHeader()
    this.getTestList(this.$route.params.courseId)
  },
  methods: {
    async getTestList(id) {
      const { data: res } = await this.$http.get(`/courses/getTestList/${id}`)
      this.testList = res.data
      console.log(this.testList)
    },

    showNewTestForm() {
      this.testFormName = 'Create a Audio Test'
      this.testForm = {}
      this.testFormModal.show()
    },
    async showEditTestForm(id) {
      this.testFormName = 'Edit a Audio Test'
      const { data: res } = await this.$http.get(`/audioTests/findOne/${id}`)
      this.testForm = res.data
      this.testFormModal.show()
    },
    async saveOrUpdateTest() {
      if (this.testForm.id) {
        // update an existing chapter
        await this.$http.put(`/audioTests/update`, this.testForm)
      } else {
        // save a new chapter
        this.testForm.id = this.objectId()
        this.testForm.active = true
        await this.$http.post(`/audioTests/save/${this.$route.params.courseId}`, this.testForm)
      }
      this.getTestList(this.$route.params.courseId)
      this.testFormModal.hide()
    },

    async deleteTest(id) {
      if (confirm('Do you want to delete this Test?')) {
        const { data: res } = await this.$http.delete(`/audioTests/delete/${id}`)
        if (res.status == 200) {
          //delete Chapter with id from list
          //Step1, find index of the Chapter to be deleted
          let index = this.testList.findIndex((test) => {
            return test.id == id
          })
          //Step2, delete this Chapter
          this.testList.splice(index, 1)
        } else {
          alert('Cannot delete Test!')
        }
      }
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

    validateNumber: (event) => {
      let keyCode = event.keyCode
      if (keyCode < 48 || keyCode > 57) {
        event.preventDefault()
      }
    },

    startTest(test) { 
      this.$router.push({ path: `/audioTestTaking/${this.$route.params.courseId}/${test.id}` })
    },

    viewAnswer(id) {
      this.$router.push({ path: `/audioAnswerList/${this.$route.params.courseId}/${id}` })
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
