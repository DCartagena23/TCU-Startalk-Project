<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <b-container>
      <Header :toggleEdit="toggleEdit" :toggleAssignment="toggleAssignment" :toggleTextbook="toggleTextbook" :toggleExam="toggleExam" :toggleForum="toggleForum" :toggleHome="toggleHome" :toggleAccount="toggleAccount" :accountMode="accountMode" />
      <Home v-show="toggleHomeBool" />
      <Body :toggleEditButton="toggleEditButton" v-show="toggleTextbookBool"/>
      <Assignment v-show="toggleAssignmentBool" />
      <Exam v-show="toggleExamBool" />
      <Forum v-show="toggleForumBool" />
      <Account v-show="toggleAccountBool" />
      <Footer  />
    </b-container>
  </div>
</template>

<script>
import Header from './views/Header.vue'
import Body from './views/Body.vue'
import Footer from './views/Footer.vue'
import Home from './views/Home.vue'
import Assignment from './views/Assignment.vue'
import Exam from './views/Exam.vue'
import Forum from './views/Forum.vue'
import Account from './views/Account.vue'

export default {
  name: 'App',
  components: {
    Header,
    Body,
    Footer,
    Home,
    Assignment,
    Exam,
    Forum,
    Account
  },
  data:function(){
    return{
      toggleEditButton:true,
      toggleTextbookBool:true,
      toggleAssignmentBool:false,
      toggleExamBool:false,
      toggleForumBool:false,
      toggleAccountBool:false,
      toggleHomeBool:false,
      accountMode:"Student"
    }
  },
  methods:{
    toggleEdit: function(){
            this.toggleEditButton = !(this.toggleEditButton);
            if(this.accountMode == "Student"){
              this.accountMode = "Teacher"
            }else{
              this.accountMode = "Student"
            }
    },
    toggleTextbook: function(){
      this.toggleTextbookBool = true;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleAccountBool = false;
      this.toggleHomeBool = false;
    },
    toggleAssignment: function(){
      this.toggleAssignmentBool=true;
      this.toggleTextbookBool=false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleHomeBool = false;
      this.toggleAccountBool = false;

    },
    toggleExam: function(){
      this.toggleExamBool=true;
      this.toggleTextbookBool=false;
      this.toggleAssignmentBool = false;
      this.toggleHomeBool = false;
      this.toggleAccountBool = false;
      this.toggleForumBool = false;
    },
    toggleForum: function(){
      this.toggleForumBool=true;
      this.toggleTextbookBool=false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleAccountBool = false;
    },
    toggleHome: function(){
      this.toggleHomeBool=true;
      this.toggleTextbookBool=false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleAccountBool = false;
    },
    toggleAccount:function(){
      this.toggleAccountBool = true;
      this.toggleTextbookBool = false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 15px;
}

</style>
