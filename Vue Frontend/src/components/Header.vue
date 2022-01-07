<template>
    <div class="row">
        <div class="col">
                <!-- <Logo :toggleHome="toggleHome" /> -->
                <img src="../assets/startalk.png" alt="website logo" class="logo" v-on:click="toggleHome">
        </div>
        <div class="col-6">
            <div>
                <!-- <div class="nav" v-on:click="toggleAccount">Textbook</div>
                <div class="nav" v-on:click="toggleAccount">Assignment</div>
                <div class="nav" v-on:click="toggleAccount">Exam</div>
                <div class="nav" v-on:click="toggleAccount">Forum</div> -->
                <NavButton title="Textbook" :toggleFunction="toggleTextbook" />
                <NavButton title="Assignment" :toggleFunction="toggleAssignment" />
                <NavButton title="Exam" :toggleFunction="toggleExam"/>
                <NavButton title="Grades" :toggleFunction="toggleGrades"/>
                <NavButton title="Forum" :toggleFunction="toggleForum"/>
            </div>
        </div>
        <div class="col">
                <!-- <div class="nav2" v-show="toggle"> Account </div> -->
                <!-- <AccountDrop :toggleEdit="toggleEdit" :toggleAccount="toggleAccount" :accountMode="accountMode" /> -->
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
        </div>
    </div>
</template>

<style scoped>

.nav:hover{
    color:blue;
    text-decoration: none;
}

.nav{
    /* border-style:solid;
    border-width:0 1px 0 1px; */
    float: left;
    font-size: 20px;
    margin: 18px;
}

.nav2{
    /* border-style:solid;
    border-width:0 1px 0 1px; */
    float: right;
    font-size: 20px;
    margin: 18px;
}

#acclogo{
    height:15%;
    width:15%;
    margin-left: auto;
    margin-right: auto;
    display: block;
}

.logo{
    height:50%;
    width:50%;
    float: left;
    cursor: pointer;
    /* margin-left: auto;
    margin-right: auto;
    display: block; */
}

.section:hover{
    color: blue;
    cursor: pointer;
}
</style>

<script>
import NavButton from './NavButton.vue'
// import AccountDrop from './AccountDrop.vue'
// import Logo from "./Logo.vue"

export default {
    name: 'Header',
    components:{
        NavButton
    },
    props:{
        toggleEdit:Function,
        toggleTextbook:Function,
        toggleAssignment:Function,
        toggleExam:Function,
        toggleForum:Function,
        toggleHome:Function,
        toggleAccount:Function,
        toggleGrades:Function,
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