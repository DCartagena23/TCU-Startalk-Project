<template>
  <table class="table table-striped table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Author</th>
        <th>Description</th>
        <th>Price</th>
        <th>Operations</th>
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
  emits: ['edit', 'delete','view'],
}
</script>
