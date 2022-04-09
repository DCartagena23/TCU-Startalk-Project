<template>
<div>
  <div class="container">
    <!-- Homework List -->
    <div class="py-6">
    <h1 class="display-6" style="text-align:center;">Homework List</h1>
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
      @click.prevent="showNewHwForm"
      >
      Add a New Homework
      </button>
    </div>
    </div>
    <!-- <hr /> -->
    <table class="table table-hover">
      <thead>
        <tr>
          <th style="width: 200px;">Id</th>
          <th>Title</th>
          <th style="width: 100px; text-align: center;" v-if=checkRole() >Operations</th>
        </tr> 
      </thead>
      <tbody>
        <tr :key="homework.id" v-for="homework in homeworkList">
          <td style="width: 200px;" v-if="homework.active">{{ homework.id }}</td>
          <td @click.prevent="view(homework.id)" onmouseover="this.style.color='orange';" onmouseout="this.style.color='';" v-if="homework.active">{{ homework.title }}</td>
          <td style="width: 100px; text-align: center;" v-if="homework.active && checkRole()">
            <div class="btn-group">
              <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                <font-awesome-icon :icon="['fas', 'bars']" />
              </button>
              <ul class="dropdown-menu" style="min-width: 30px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="showEditHwForm(homework.id)"><font-awesome-icon :icon="['fas', 'gear']" /> </a></li> 
                <li><a class="dropdown-item" @click.prevent="deleteHw(homework.id)"><font-awesome-icon :icon="['fas', 'trash']" /></a></li>
              </ul>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="modal fade" id="homeworkForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ homeworkFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Homework Name:</label>
              <input type="text" class="form-control" v-model="homeworkForm.title" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="saveOrUpdateHw">Save</button>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Homework',

  data: function () {
    return {
      homeworkList: [],
      book: {},
      chapter: {},

      homeworkForm: {},
      homeworkFormName: '',
      homeworkFormModal: null,
    }
  },
  mounted: function () {
    this.homeworkFormModal = new this.$bootstrap.Modal(document.getElementById('homeworkForm'), {})
  },
  created() {
    this.setHeader()
    this.getHwList(this.$route.params.courseId)
  },
  methods: {
    async getHwList(id) {
      const { data: res } = await this.$http.get(`/courses/getHomeworkList/${id}`)
      this.homeworkList = res.data
      console.log(this.homeworkList)
    },

    showNewHwForm() {
      this.homeworkFormName = 'Create a Homework'
      this.homeworkForm = {}
      this.homeworkFormModal.show()
    },
    async showEditHwForm(id) {
      this.homeworkFormName = 'Edit a Homework'
      const { data: res } = await this.$http.get(`/homeworks/findOne/${id}`)
      this.homeworkForm = res.data
      this.homeworkFormModal.show()
    },
    async saveOrUpdateHw() {
      if (this.homeworkForm.id) {
        // update an existing chapter
        await this.$http.put(`/homeworks/update`, this.homeworkForm)
      } else {
        // save a new chapter
        this.homeworkForm.id = this.objectId()
        this.homeworkForm.active = true
        await this.$http.post(`/homeworks/save/${this.$route.params.courseId}`, this.homeworkForm)
      }
      this.getHwList(this.$route.params.courseId)
      this.homeworkFormModal.hide()
    },

    async deleteHw(id) {
      if (confirm('Do you want to delete this Homework?')) {
        const { data: res } = await this.$http.delete(`/homeworks/delete/${id}`)
        if (res.status == 200) {
          //delete Chapter with id from list
          //Step1, find index of the Chapter to be deleted
          let index = this.homeworkList.findIndex((homework) => {
            return homework.id == id
          })
          //Step2, delete this Chapter
          this.homeworkList.splice(index, 1)
        } else {
          alert('Cannot delete Homework!')
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

    view(id) {
      this.$router.push({ path: `/audioTest/${this.$route.params.courseId}/${id}` })
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
