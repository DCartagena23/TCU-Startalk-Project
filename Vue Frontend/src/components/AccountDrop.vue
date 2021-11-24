<template>
   <!-- <b-dropdown id="dropdown-1" text="Account" variant="none" class="m-md-2">
    <b-dropdown-item v-on:click="toggleAccount">Edit Account Information</b-dropdown-item>
    <b-dropdown-item v-on:click="toggleEdit">Switch to {{accountMode}} View</b-dropdown-item>
  </b-dropdown> -->
  <!-- <div class="dropdown">
    <button class="btn btn-outline-dark dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      Account
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
      <a class="dropdown-item">Edit Account Info</a>
      <a class="dropdown-item">Switch to {{accountMode}}</a>
    </div>
  </div>  -->
  <div id="accordion1" style="margin: 8px 35px 0 0; float: right;">
    <div class="card">
      <div class="card-header" id="headingFour">
        <h5 class="mb-0">
          <button id="account" class="btn btn section" v-on:click="toggleAccDrop" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
            Account
          </button>
        </h5>
      </div>
      <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion1">
        <div class="card-body section" v-on:click="toggleAccount">
          Edit Account Information
        </div>
        <div class="card-body section" v-on:click="toggleEdit">
          Switch to {{accountMode}}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.section:hover{
    color: blue;
    cursor: pointer;
}
</style>

<script>
export default {
    name: 'AccountDrop',
    props:{
        toggleEdit:Function,
        toggleAccount:Function,
        accountMode:String
    },
    methods:{
      toggleAccDrop:function(){
        var accountDrop = document.getElementById('collapseFour');
        accountDrop.classList.toggle('show');
        }
    }
}
</script>