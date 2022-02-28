<template>
  <td>{{ book.id }}</td>
  <td>{{ book.title }}</td>
  <td>{{ book.author }}</td>
  <td>{{ book.desc }}</td>
  <td>
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
