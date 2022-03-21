<template>
  <td v-if="book.active">{{ book.id }}</td>
  <td v-if="book.active">{{ book.title }}</td>
  <td v-if="book.active">{{ book.author }}</td>
  <td v-if="book.active">{{ book.desc }}</td>
  <td v-if="book.active">
    <!-- <a class="btn btn-warning" v-if=checkRole() style="margin-right: 10px" @click.prevent="$emit('edit', book.id)">Edit</a> -->
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
      @click.prevent="$emit('edit', book.id)"
      >
      Edit
    </button>
    <!-- <a class="btn btn-danger" v-if=checkRole() style="margin-right: 10px" @click.prevent="$emit('delete', book.id)">Delete</a> -->
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
      @click.prevent="$emit('delete', book.id)"
      >
      Delete
    </button>
    <!-- <a class="btn btn-success" @click.prevent="$emit('view', book.id)">View</a> -->
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
      @click.prevent="$emit('view', book.id)"
      >
      View
    </button>
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
