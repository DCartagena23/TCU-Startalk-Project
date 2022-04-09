<template>
  <td style="width: 200px;" v-if="book.active">{{ book.id }}</td>
  <td style="min-width: 200px;" 
      @click.prevent="$emit('view', book.id)" 
      onmouseover="this.style.color='orange';" onmouseout="this.style.color='';" 
      v-if="book.active">
      {{ book.title }}</td>
  <td style="min-width: 200px;" v-if="book.active">{{ book.author }}</td>
  <td v-if="book.active">{{ book.desc }}</td>
  <td v-if="book.active && checkRole()" style="width: 100px; text-align: center;">
                <div class="btn-group">
              <button type="button" class="btn" data-bs-toggle="dropdown" aria-expanded="false">
                <font-awesome-icon :icon="['fas', 'bars']" />
              </button>
              <ul class="dropdown-menu" style="min-width: 30px; text-align: center;">
                <li><a class="dropdown-item" @click.prevent="$emit('edit', book.id)"><font-awesome-icon :icon="['fas', 'gear']" /> </a></li> 
                <li><a class="dropdown-item" @click.prevent="$emit('delete', book.id)"><font-awesome-icon :icon="['fas', 'trash']" /></a></li>
              </ul>
            </div>
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
