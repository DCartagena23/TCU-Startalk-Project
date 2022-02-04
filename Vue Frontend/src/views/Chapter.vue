<template>
  <div class="container">
    <!-- Chapter List -->
    <h1>{{ book.title }}</h1>
    <div>
      <a class="btn btn-primary" @click.prevent="showNewchapterForm">Add a New Chapter</a>
    </div>
    <hr />
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Chapter</th>
          <th>Name</th>
          <th>Operations</th>
        </tr>
      </thead>
      <tbody>
        <tr :key="chapter.id" v-for="chapter in chapList">
          <td>Chapter {{ chapter.number }}</td>
          <td>{{ chapter.title }}</td>
          <td>
            <a class="btn btn-warning" style="margin-right: 10px" @click.prevent="showEditchapterForm(chapter.id)">Edit</a>
            <a class="btn btn-danger" style="margin-right: 10px" @click.prevent="deleteChapter(chapter.id)">Delete</a>
            <a class="btn btn-success" style="margin-right: 10px" @click.prevent="view(chapter)">View</a>
            <a class="btn btn-success" @click.prevent="studentView(chapter)">Student View</a>
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
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Chapter',

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
    this.getChapList(this.$route.params.id)
    this.getBook(this.$route.params.id)
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

    checkBook(id) {
      this.check = id
      console.log(this.check)
      this.$router.push({
        name: 'About',
        params: { id: this.check },
      })
    },
    view(chapter) {
      console.log(chapter)
      this.$router.push({ path: `/read/${chapter.id}` })
    },
    studentView(chapter) {
      console.log(chapter)
      this.$router.push({ path: `/student/${chapter.id}` })
    },
  },
}
</script>

<style scoped></style>
