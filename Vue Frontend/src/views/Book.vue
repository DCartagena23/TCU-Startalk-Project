<template>
  <div class="container">
    <!-- Book List -->
    <h1>Book List</h1>
    <div>
      <a class="btn btn-primary" style="margin-right: 10px" @click.prevent="getBookList">Show All Book</a>
      <a class="btn btn-primary" v-if=checkRole() @click.prevent="showNewBookForm">Add a New Book</a>
      <h6 class="card-title" style="font-size: 2em">Search by Id</h6>
      <input v-model="idSearch" />
      <button type="button" class="btn btn-success" style="margin-left: 10px" @click.prevent="search(idSearch)">Search</button>
    </div>
    <hr />
    <BookList :bookList="database" @delete="deleteBook" @edit="showEditBookForm" @view="view"></BookList>
    <!-- Book List -->

    <!-- Add a New Book Form -->
    <div class="modal fade" id="bookForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              {{ bookFormName }}
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="book-name" class="col-form-label">Title:</label>
                <input type="text" class="form-control" id="book-title" v-model="bookForm.title" />
              </div>
              <div class="mb-3">
                <label for="book-author" class="col-form-label">Author:</label>
                <input type="text" class="form-control" id="book-author" v-model="bookForm.author" />
              </div>
              <div class="mb-3">
                <label for="book-description" class="col-form-label">Description:</label>
                <input type="text" class="form-control" id="book-description" v-model="bookForm.desc" />
              </div>
              <div class="mb-3">
                <label for="book-price" class="col-form-label">Price:</label>
                <input type="text" class="form-control" id="book-price" v-model="bookForm.price" />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="saveOrUpdateBook">Save</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Add a New Book Form -->
  </div>
</template>

<script>
// @ is an alias to /src
import BookList from '@/components/BookList'

export default {
  name: 'Book',
  components: {
    BookList,
  },
  data: function () {
    return {
      database: [],
      bookForm: {},
      bookFormName: '',
      bookFormModal: null,
      idSearch: '',
    }
  },
  mounted: function () {
    this.$http.defaults.headers.common['Authorization'] = this.$store.state.auth.token;
    this.bookFormModal = new this.$bootstrap.Modal(document.getElementById('bookForm'), {})
  },
  methods: {
    async getBookList() {
      const { data: res } = await this.$http.get('/books/findAll')
      this.database = res.data
    },
    showNewBookForm() {
      this.bookFormName = 'Add a New Book'
      this.bookForm = {}
      this.bookFormModal.show()
    },
    async showEditBookForm(id) {
      this.bookFormName = 'Edit a Book'
      const { data: res } = await this.$http.get(`/books/findOne/${id}`)
      this.bookForm = res.data
      this.bookFormModal.show()
    },
    async search(id) {
      try {
        const { data: res } = await this.$http.get(`/books/findOne/${id}`)
        if (res.status == 200) {
          this.database = []
          this.database = [...this.database, res.data]
        }
      } catch (error) {
        alert('No Book Found!')
      }
    },
    async saveOrUpdateBook() {
      if (this.bookForm.id) {
        // update an existing Book
        const { data: res } = await this.$http.put('/books/updateBook', this.bookForm)
        let index = this.database.findIndex((book) => book.id == res.data.id)
        this.database[index] = res.data
      } else {
        // save a new Book
        this.bookForm.id = this.objectId()
        this.bookForm.active = false
        const { data: res } = await this.$http.post('/books/saveBook', this.bookForm)
        this.book = [...this.database, res.data]
        this.getBookList()
      }
      this.bookFormModal.hide()
    },
    async deleteBook(id) {
      if (confirm('Do you want to delete this Book?')) {
        const { data: res } = await this.$http.delete(`/books/deleteBook/${id}`)
        if (res.status == 200) {
          //delete Book with id from list
          //Step1, find index of the Book to be deleted
          let index = this.database.findIndex((book) => {
            return book.id == id
          })
          //Step2, delete this Book
          this.database.splice(index, 1)
        } else {
          alert('Cannot delete Book!')
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
      this.$router.push({ path: `/chapter/${id}` })
    },
    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },
  },
}
</script>

<style scoped></style>
>
