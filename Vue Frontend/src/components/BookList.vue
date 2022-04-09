<template>
  <table class="table table-hover">
    <thead>
      <tr>
        <th style="width: 200px;">Id</th>
        <th style="min-width: 200px;">Name</th>
        <th style="min-width: 200px;" >Author</th>
        <th>Description</th>
        <th style="width: 100px; text-align: center;" v-if=checkRole()>Operations</th>
      </tr>
    </thead>
    <tbody>
      <tr :key="book.id" v-for="book in bookList">
        <BookListItem :book="book" @view="$emit('view', book.id)" @edit="$emit('edit', book.id)" @delete="$emit('delete', book.id)"></BookListItem>
      </tr>
    </tbody>
  </table>
</template>

<script>
// @ is an alias to /src
import BookListItem from '@/components/BookListItem'
export default {
  name: 'BookList',
  props: ['bookList'],
  components: {
    BookListItem,
  },
  data: function () {
    return {}
  },
  emits: ['edit', 'delete', 'view'],
  methods:{
    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },
  }
}
</script>
