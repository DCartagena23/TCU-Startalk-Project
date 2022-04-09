<template>
<div>
<Breadcrumb/>
<TeacherHeader />
  <div class="container">
    <!-- Chapter List -->
    <div class="py-10">
    <h1 class="display-6" style="text-align: center;">{{ book.title }}</h1>
    <div style="text-align: center;">
      <!-- <a class="btn btn-primary" v-if=checkRole() @click.prevent="showNewchapterForm" >Add a New Chapter</a> -->
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
      v-if=checkRole() @click.prevent="showNewchapterForm"
      >
      Create chapter
      </button>
    </div>
    </div>
    <hr />
    <table class="table table-hover">
      <thead>
        <tr>
          <th>Chapter</th>
          <th>Name</th>
          <th style="width: 100px; text-align: center;" v-if=checkRole() >Operations</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="chapter.id" v-for="chapter in chapList">
          <td>Chapter {{ chapter.number }}</td>
          <td @click.prevent="view(chapter)" onmouseover="this.style.color='orange';" onmouseout="this.style.color='';">{{ chapter.title }}</td>
          <td v-if=checkRole() style="width: 100px; text-align: center;">
            <div class="btn-group">
              <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                <font-awesome-icon :icon="['fas', 'bars']" />
              </button>
              <ul class="dropdown-menu" style="min-width: 30px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="showEditchapterForm(chapter.id)"><font-awesome-icon :icon="['fas', 'gear']" /> </a></li> 
                <li><a class="dropdown-item" @click.prevent="studentView(chapter)"><span title="Student View."><font-awesome-icon :icon="['fas', 'users']" /></span></a></li>    
                <li><a class="dropdown-item" @click.prevent="deleteChapter(chapter.id)"><font-awesome-icon :icon="['fas', 'trash']" /></a></li>              </ul>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Add a New Chapter Form -->
  </div>
  <div class="modal fade" id="chapterForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            {{ chapterFormName }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Chapter Number:</label>
              <input type="text" @keypress="validateNumber" class="form-control" id="chapter-title" v-model="chapterForm.number" />
            </div>
            <div class="mb-3">
              <label for="chapter-name" class="col-form-label">Title:</label>
              <input type="text" class="form-control" id="chapter-title" v-model="chapterForm.title" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="saveOrUpdateChapter">Save</button>
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
  name: 'Chapter',
  components: {
    Breadcrumb,
    TeacherHeader,
  },
  data: function () {
    return {
      chapList: [],
      book: {},
      chapter: {},

      chapterForm: {},
      chapterFormName: '',
      chapterFormModal: null,
    }
  },
  mounted: function () {
    this.chapterFormModal = new this.$bootstrap.Modal(document.getElementById('chapterForm'), {})
  },
  created() {
    this.setHeader()
    this.getBook(this.$route.params.bookId)
  },
  methods: {
    async getChapList(id) {
      const { data: res } = await this.$http.get(`/books/getChapters/${id}`)
      this.chapList = res.data
      this.sorted()
    },
    async getBook(id) {
      const { data: res } = await this.$http.get(`/books/findOne/${id}`)
      if (res.status == 200) {
        this.book = res.data
        this.chapList = this.book.chapter
        this.sorted()
      }
    },

    async getChapter(id) {
      const { data: res } = await this.$http.get(`/chapters/findOne/${id}`)
      if (res.status == 200) {
        this.chapter = res.data
      }
    },

    showNewchapterForm() {
      this.chapterFormName = 'Add a New chapter'
      this.chapterForm = {}
      this.chapterFormModal.show()
    },
    async showEditchapterForm(id) {
      this.chapterFormName = 'Edit a chapter'
      const { data: res } = await this.$http.get(`/chapters/findOne/${id}`)
      this.chapterForm = res.data
      this.chapterFormModal.show()
    },
    async saveOrUpdateChapter() {
      if (this.chapterForm.id) {
        // update an existing chapter
        const { data: res } = await this.$http.put(`/chapters/updateChapter/${this.book.id}`, this.chapterForm)
        let index = this.chapList.findIndex((chapter) => chapter.id == res.data.id)
        this.chapList[index] = res.data
        this.sorted()
      } else {
        // save a new chapter
        this.chapterForm.id = this.objectId()
        this.chapterForm.active = false
        const { data: res } = await this.$http.post(`/chapters/saveChapter/${this.book.id}`, this.chapterForm)
        this.chapter = [...this.chapList, res.data]
        this.getChapList(this.book.id)
      }
      this.chapterFormModal.hide()
    },
    async deleteChapter(id) {
      if (confirm('Do you want to delete this Chapter?')) {
        const { data: res } = await this.$http.delete(`/chapters/deleteChapter/${id}`)
        if (res.status == 200) {
          //delete Chapter with id from list
          //Step1, find index of the Chapter to be deleted
          let index = this.chapList.findIndex((chapter) => {
            return chapter.id == id
          })
          //Step2, delete this Chapter
          this.chapList.splice(index, 1)
        } else {
          alert('Cannot delete Chapter!')
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

    sorted() {
      return this.chapList.sort((a, b) => {
        return a.number - b.number
      })
    },

    validateNumber: (event) => {
      let keyCode = event.keyCode
      if (keyCode < 48 || keyCode > 57) {
        event.preventDefault()
      }
    },

    view(chapter) {
      if (this.checkRole()) this.$router.push({ path: `/read/${this.$route.params.courseId}/${this.$route.params.bookId}/${chapter.id}` })
      else this.$router.push({ path: `/student/${this.$route.params.courseId}/${this.$route.params.bookId}/${chapter.id}` })
    },
    studentView(chapter) {
      console.log(chapter)
      this.$router.push({ path: `/student/${this.$route.params.courseId}/${this.book.id}/${chapter.id}` })
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
