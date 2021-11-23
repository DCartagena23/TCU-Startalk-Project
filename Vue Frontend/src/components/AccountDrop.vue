<template>
<div style="margin: 8px 35px 0 0; float: right;">
   <!-- <b-dropdown id="dropdown-1" text="Account" variant="none" class="m-md-2">
    <b-dropdown-item v-on:click="toggleAccount">Edit Account Information</b-dropdown-item>
    <b-dropdown-item v-on:click="toggleEdit">Switch to {{accountMode}} View</b-dropdown-item>
  </b-dropdown> -->
  <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Dropdown button
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" v-on:click="toggleAccount">Edit Account Info</a>
    <a class="dropdown-item" v-on:click="toggleEdit">Switch to {{accountMode}}</a>
  </div>
</div> 
</div>
</template>

<style scoped>
</style>

<script>
export default {
    name: 'AccountDrop',
    props:{
        toggleEdit:Function,
        toggleAccount:Function,
        accountMode:String
    }
}
</script>