<template>
  <div class="nav" v-on:click="toggleFunction">{{ title }}</div>
</template>

<style scoped>
.nav:hover {
  color: blue;
  text-decoration: none;
  cursor: pointer;
}

.nav {
  /* border-style:solid;
    border-width:0 1px 0 1px; */
  float: left;
  font-size: 20px;
  margin: 18px;
}
</style>

<script>
export default {
  name: 'NavButton',
  props: {
    title: String,
    toggleFunction: Function,
  },
}
</script>
