<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <b-container>
      <Header :toggleEdit="toggleEdit" :toggleAssignment="toggleAssignment" :toggleTextbook="toggleTextbook" :toggleExam="toggleExam" :toggleGrades="toggleGrades" :toggleForum="toggleForum" :toggleHome="toggleHome" :toggleAccount="toggleAccount" :accountMode="accountMode" />
      <Home v-show="toggleHomeBool" />
      <Body :toggleEditButton="toggleEditButton" v-show="toggleTextbookBool" :toggleEditPassage="toggleEditPassage"/>
      <Assignment v-show="toggleAssignmentBool" />
      <Exam v-show="toggleExamBool" />
      <Grades v-show="toggleGradesBool" />
      <Forum v-show="toggleForumBool" />
      <Account v-show="toggleAccountBool" />
      <EditPassage v-show="togglePassageBool" :toggleEditChapter="toggleEditChapter" />
      <EditChapter v-show="toggleChapterBool" />
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
import Grades from './views/Grades.vue'
import Forum from './views/Forum.vue'
import Account from './views/Account.vue'
import EditPassage from './views/EditPassage.vue'
import EditChapter from './views/EditChapter.vue'

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
    Account,
    EditPassage,
    EditChapter,
    Grades
  },
  data:function(){
    return{
      toggleEditButton:true,
      toggleTextbookBool:true,
      toggleAssignmentBool:false,
      toggleExamBool:false,
      toggleGradesBool:false,
      toggleForumBool:false,
      toggleAccountBool:false,
      toggleHomeBool:false,
      togglePassageBool:false,
      toggleChapterBool:false,
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
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleAssignment: function(){
      this.toggleAssignmentBool=true;
      this.toggleTextbookBool=false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleHomeBool = false;
      this.toggleAccountBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;

    },
    toggleExam: function(){
      this.toggleExamBool=true;
      this.toggleTextbookBool=false;
      this.toggleAssignmentBool = false;
      this.toggleHomeBool = false;
      this.toggleAccountBool = false;
      this.toggleForumBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleGrades: function(){
      this.toggleGradesBool = true;
      this.toggleExamBool=false;
      this.toggleTextbookBool=false;
      this.toggleAssignmentBool = false;
      this.toggleHomeBool = false;
      this.toggleAccountBool = false;
      this.toggleForumBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleForum: function(){
      this.toggleForumBool=true;
      this.toggleTextbookBool=false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleAccountBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleHome: function(){
      this.toggleHomeBool=true;
      this.toggleTextbookBool=false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleAccountBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleAccount:function(){
      this.toggleAccountBool = true;
      this.toggleTextbookBool = false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.togglePassageBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleEditPassage:function(){
      this.togglePassageBool = true;
      this.toggleAccountBool = false;
      this.toggleTextbookBool = false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleChapterBool = false;
      this.toggleGradesBool = false;
    },
    toggleEditChapter:function(){
      this.togglePassageBool = false;
      this.toggleAccountBool = false;
      this.toggleTextbookBool = false;
      this.toggleHomeBool = false;
      this.toggleAssignmentBool = false;
      this.toggleExamBool = false;
      this.toggleForumBool = false;
      this.toggleChapterBool = true;
      this.toggleGradesBool = false;
    },
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
