<template>
  <div>
    <Breadcrumb/>
    <TeacherHeader />
  <div class="container">
    <!-- <hr /> -->
    <div class="py-10">
    <h6 class="display-3" v-if="flag" style="text-align: center;">Your Course</h6>
    <h6 class="display-3" v-else style="text-align: center;">All Courses</h6>
    <div class="py-6" style="text-align: center;">
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
      ease-in-out" style="align-self:center;"
      @click.prevent="toggle"
      v-if="flag"
      >
      View All Courses
      </button>
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
      ease-in-out" style="align-self:center;"
      @click.prevent="toggle"
      v-else
      >
      View Your Course
      </button>
    </div>
    <!-- <hr /> -->
    <!-- <div style="text-align:center;">
      <a v-if="flag" class="btn btn-primary" @click.prevent="toggle">View All Course</a>
      <a v-else class="btn btn-primary" @click.prevent="toggle">View Your Course</a>
    </div> -->
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>ID</th>
          <th>Course</th>
          <th>Active</th>
          <th>Teacher</th>
          <th>Operations</th>
        </tr>
      </thead>
      <tbody v-if="flag">
        <tr :key="course.id" v-for="course in database">
          <td v-if="yourCourse(course) && course.active">{{ course.id }}</td>
          <td v-if="yourCourse(course) && course.active">{{ course.title }}</td>
          <td v-if="yourCourse(course) && course.active">{{ course.active }}</td>
          <td v-if="yourCourse(course) && course.active">{{ course.teacher.username }}</td>
          <td v-if="yourCourse(course) && course.active">
            <!-- <a class="btn btn-warning" v-if=checkRole() :class="{ invisible: !yourCourse(course) }" style="margin-right: 10px" @click.prevent="showEditCourseForm(course.id)">Edit</a> -->
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
      @click.prevent="showEditCourseForm(course.id)"
      :class="{ invisible: !yourCourse(course) }"
      >
      Edit
            </button>
            <!-- <a class="btn btn-danger" v-if=checkRole() :class="{ invisible: !yourCourse(course) }" style="margin-right: 10px" @click.prevent="deleteCourse(course.id)">Delete</a> -->
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
      @click.prevent="deleteCourse(course.id)"
      :class="{ invisible: !yourCourse(course) }"
      >
      Delete
            </button>
            <!-- <a class="btn btn-success" style="margin-right: 10px" @click.prevent="view(course)">View</a> -->
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
      @click.prevent="view(course)"
      :class="{ invisible: !yourCourse(course) }"
      >
      View
            </button>
          </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr :key="course.id" v-for="course in database">
          <td v-if="course.active">{{ course.id }}</td>
          <td v-if="course.active">{{ course.title }}</td>
          <td v-if="course.active">{{ course.active }}</td>
          <td v-if="course.active">{{ course.teacher.username }}</td>
          <td v-if="course.active">
            <a class="btn btn-warning" v-if=checkRole() :class="{ invisible: !yourCourse(course) }" style="margin-right: 10px" @click.prevent="showEditCourseForm(course.id)">Edit</a>
            <a class="btn btn-danger" v-if=checkRole() :class="{ invisible: !yourCourse(course) }" style="margin-right: 10px" @click.prevent="deleteCourse(course.id)">Delete</a>
            <a class="btn btn-success" v-if=checkRole() :class="{ invisible: !yourCourse(course) }" style="margin-right: 10px" @click.prevent="view(course)">View</a>
            <a class="btn btn-success" style="margin-right: 10px" @click.prevent="joinCourse(course)">Join</a>
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
              <input type="text" @keypress="validateNumber" class="form-control" id="chapter-title" v-model="courseForm.title" />
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
          this.database = []
          this.database = [...this.database, res.data]
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
    toggle(){ 
      this.flag = !this.flag
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
