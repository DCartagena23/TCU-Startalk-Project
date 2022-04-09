<template>
  <div>

    <Breadcrumb/>
    <TeacherHeader />
  <div class="container">
    <!-- <hr /> -->
    <div class="py-10">
          <form>
            <div style="display : inline-flex " class="mb-3">
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
      @click.prevent="search(idsearch)">
      ID Search
      </button>
              <input style="width:300px" type="text" class="form-control" id="chapter-title" v-model="idsearch" />
            </div>
          </form>
    <h6 class="display-3" v-if="flag" style="text-align: center;">Your Course</h6>

    <div v-if="checkRole()" class="py-6" style="text-align: center;">
      <!-- <a class="btn btn-primary" v-if=checkRole() @click.prevent="showNewCourseForm">Add a New Course</a> -->
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
      v-if=checkRole() @click.prevent="showNewCourseForm">
      Add a New Course
      </button>
    </div>
    <!-- <hr /> -->
    <!-- <div style="text-align:center;">
      <a v-if="flag" class="btn btn-primary" @click.prevent="toggle">View All Course</a>
      <a v-else class="btn btn-primary" @click.prevent="toggle">View Your Course</a>
    </div> -->
    </div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th style="width: 200px;">ID</th>
          <th>Course</th>
          <th>Active</th>
          <th>Teacher</th>
          <th style="width: 100px; text-align: center;" v-if=checkRole()>Operations</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="course.id" v-for="course in database">
          <td v-if="yourCourse(course) && course.active" style="width: 200px;">{{ course.id }}</td>
          <td @click.prevent="view(course)" onmouseover="this.style.color='orange';" onmouseout="this.style.color='';" v-if="yourCourse(course) && course.active">{{ course.title }}</td>
          <td v-if="yourCourse(course) && course.active">{{ course.active }}</td>
          <td v-if="yourCourse(course) && course.active">{{ course.teacher.username }}</td>
          <td v-if="yourCourse(course) && course.active && checkRole()" style="width: 100px; text-align: center;">
            <div class="btn-group">
              <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                <font-awesome-icon :icon="['fas', 'bars']" />
              </button>
              <ul class="dropdown-menu" style="min-width: 30px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="showEditCourseForm(course.id)"><font-awesome-icon :icon="['fas', 'gear']" /> </a></li> 
                <li><a class="dropdown-item" @click.prevent="deleteCourse(course.id)"><font-awesome-icon :icon="['fas', 'trash']" /></a></li>
              </ul>
            </div>
          </td>
        </tr>
      </tbody>

    </table>

    <!-- Add a New Chapter Form -->
  </div>
  <div class="modal fade" id="courseForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ courseFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Course Title:</label>
              <input type="text" class="form-control" id="chapter-title" v-model="courseForm.title" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="saveOrUpdateCourse">Save</button>
        </div>
      </div>
    </div>
    </div>

      <div class="modal fade" id="searchForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            Search Result
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Course Title:</label>
              <input disabled type="text" class="form-control" id="chapter-title" v-model="searchForm.title" />
            </div>
          </form>
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Course Teacher:</label>
              <input disabled type="text" class="form-control" id="chapter-title" v-model="teacherUsername" />
            </div>  
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-success" @click="joinCourse(searchForm)">Join</button>
        </div>
      </div>
    </div>
    </div>
    </div>
</template>

<script>
// @ is an alias to /src
import Breadcrumb from '@/components/Breadcrumb.vue'
import TeacherHeader from '@/components/TeacherHeader.vue'
export default {
  name: 'Home',

  data: function () {
    return {
      database:[],
      flag: true,
      idsearch:"",
      teacherUsername:"",

      searchForm: {},
      searchFormModal: null,

      courseForm: {},
      courseFormName: '',
      courseFormModal: null,
    }
  },
  components: {
    Breadcrumb,
    TeacherHeader,
  },
  mounted: function () {
    this.courseFormModal = new this.$bootstrap.Modal(document.getElementById('courseForm'), {})
    this.searchFormModal = new this.$bootstrap.Modal(document.getElementById('searchForm'), {})

  },
  created() {
    this.setHeader()
    this.getCourseList(this.$route.params.id)
  },
  methods: {
    async getCourseList() {
      const { data: res } = await this.$http.get('/courses/findAll')
      this.database = res.data
    },
    showNewCourseForm() {
      this.courseFormName = 'Add a New Course'
      this.courseForm = {}
      this.courseFormModal.show()
    },
    async showEditCourseForm(id) {
      this.courseFormName = 'Edit a Course'
      const { data: res } = await this.$http.get(`/courses/findOne/${id}`)
      this.courseForm = res.data
      this.courseFormModal.show()
    },
    async search(id) {
      try {
        const { data: res } = await this.$http.get(`/courses/findOne/${id}`)
        if (res.status == 200) {
          const { data: res } = await this.$http.get(`/courses/findOne/${id}`)
          this.searchForm = res.data
          this.teacherUsername = this.searchForm.teacher.username
          this.searchFormModal.show()
        }
      } catch (error) {
        alert('No Course Found!')
      }
    },
    async saveOrUpdateCourse() {
      if (this.courseForm.id) {
        // update an existing Book
        const { data: res } = await this.$http.put('/courses/updateCourse', this.courseForm)
        let index = this.database.findIndex((course) => course.id == res.data.id)
        this.database[index] = res.data
      } else {
        // save a new Book
        this.courseForm.id = this.objectId()
        this.courseForm.active = true
        var json = {
          id: this.$store.state.auth.user.id
        }
        this.courseForm.teacher = json
        console.log(this.courseForm)
        const { data: res } = await this.$http.post('/courses/saveCourse', this.courseForm)
        this.course = [...this.database, res.data]
        console.log(res.data)
        this.getCourseList()
      }
      this.courseFormModal.hide()
    },
    async deleteCourse(id) {
      if (confirm('Do you want to delete this Course?')) {
        const { data: res } = await this.$http.delete(`/courses/deleteCourse/${id}`)
        if (res.status == 200) {
          this.getCourseList()
          }
        } else {
          alert('Cannot delete Course!')
        }
      },

    async joinCourse(course) {
      if (this.yourCourse(course)){
        alert("This is already your course.")
      }
      else{
        var data = [course.id,this.$store.state.auth.user.id]
        var chapter = {
          text: data,
        }
          const { data: res } = await this.$http.post(`/courses/joinCourse`, chapter)
          if (res.status == 200) {
            this.getCourseList()
            alert("Join Course Successful")
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
    view(course) {
      this.$router.push({ path: `/course/${course.id}` })
    },
    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },
    yourCourse(course){
      var flag = false
        if (this.$store.state.auth.user.id == course.teacher.id) flag = true
        course.students.forEach(student => {
          if (student.id == this.$store.state.auth.user.id) flag = true
        })
      return flag
    },

    setHeader(){
      var current = new Date().getTime() / 1000
      console.log(- current + this.$store.state.auth.expired)
      if (current > this.$store.state.auth.expired){
        this.$store.dispatch('auth/logout')
        this.$router.push('/login');
      }
      else this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token; 
    }
  }
}
</script>

<style scoped>
  .invisible {
    visibility: hidden;
  }
</style>
