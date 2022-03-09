<template>
  <td v-if="book.active">{{ book.id }}</td>
  <td v-if="book.active">{{ book.title }}</td>
  <td v-if="book.active">{{ book.author }}</td>
  <td v-if="book.active">{{ book.desc }}</td>
  <td v-if="book.active">
    <a class="btn btn-warning" v-if=checkRole() style="margin-right: 10px" @click.prevent="$emit('edit', book.id)">Edit</a>
    <a class="btn btn-danger" v-if=checkRole() style="margin-right: 10px" @click.prevent="$emit('delete', book.id)">Delete</a>
    <a class="btn btn-success" @click.prevent="$emit('view', book.id)">View</a>
  </td>
</template>

<script>
export default {
  name: 'BookListItem',
  props: ['book'],
  emits: ['edit', 'delete', 'view'],
  methods:{
    checkRole(){
      if (this.$store.state.auth.user.roles[0] == "ROLE_TEACHER") return true
      else return false
    },
  }
}
</script>
