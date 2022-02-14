<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <div class="container" id="student">
      <Header
        :toggleEdit="toggleEdit"
        :toggleAssignment="toggleAssignment"
        :toggleTextbook="toggleTextbook"
        :toggleExam="toggleExam"
        :toggleGrades="toggleGrades"
        :toggleForum="toggleForum"
        :toggleHome="toggleHome"
        :toggleAccount="toggleAccount"
        :accountMode="accountMode"
      />
      <Home v-show="toggleHomeBool" />
      <Textbook :toggleEditButton="toggleEditButton" v-show="toggleTextbookBool" :toggleEditMode="toggleEditMode" :textbox="textbox" />
      <Assignment v-show="toggleAssignmentBool" :toggleEditButton="toggleEditButton"/>
      <Exam v-show="toggleExamBool" :toggleEditButton="toggleEditButton"/>
      <Grades v-show="toggleGradesBool" :toggleEditButton="toggleEditButton"/>
      <Forum v-show="toggleForumBool" :toggleForumBoard="toggleForumBoard" :toggleEditButton="toggleEditButton" />
      <Account v-show="toggleAccountBool" />
      <ForumBoard v-show="toggleForumBoardBool" :toggleForumPost="toggleForumPost" />
      <ForumPost v-show="toggleForumPostBool" />
      <Footer />
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import Textbook from '@/components/Textbook.vue'
import Footer from '@/components/Footer.vue'
import Home from '@/components/Home.vue'
import Assignment from '@/components/Assignment.vue'
import Exam from '@/components/Exam.vue'
import Grades from '@/components/Grades.vue'
import Forum from '@/components/Forum.vue'
import Account from '@/components/Account.vue'
import ForumBoard from '@/components/ForumBoard.vue'
import ForumPost from '@/components/ForumPost.vue'

export default {
  name: 'App',
  components: {
    Header,
    Textbook,
    Footer,
    Home,
    Assignment,
    Exam,
    Forum,
    Account,
    Grades,
    ForumBoard,
    ForumPost,
  },
  data: function () {
    return {
      toggleEditButton: true,
      toggleTextbookBool: true,
      toggleAssignmentBool: false,
      toggleExamBool: false,
      toggleGradesBool: false,
      toggleForumBool: false,
      toggleAccountBool: false,
      toggleHomeBool: false,
      toggleForumBoardBool: false,
      toggleForumPostBool: false,
      accountMode: 'Student',
      textbox: "It didn't register",
    }
  },
  methods: {
    toggleEdit: function () {
      this.toggleEditButton = !this.toggleEditButton
      if (this.accountMode == 'Student') {
        this.accountMode = 'Teacher'
      } else {
        this.accountMode = 'Student'
      }
    },
    toggleTextbook: function () {
      this.toggleTextbookBool = true
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleAccountBool = false
      this.toggleHomeBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleAssignment: function () {
      this.toggleAssignmentBool = true
      this.toggleTextbookBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleExam: function () {
      this.toggleExamBool = true
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleGrades: function () {
      this.toggleGradesBool = true
      this.toggleExamBool = false
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleForumBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleForum: function () {
      this.toggleForumBool = true
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleHome: function () {
      this.toggleHomeBool = true
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleAccount: function () {
      this.toggleAccountBool = true
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = false
    },
    toggleForumBoard: function(){
      this.toggleAccountBool = false
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = true
      this.toggleForumPostBool = false
    },
    toggleForumPost: function(){
      this.toggleAccountBool = false
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
      this.toggleForumBoardBool = false
      this.toggleForumPostBool = true
    },
    toggleEditMode: function () {
      this.$router.push({ path: '/books' })
    },
  },
}
</script>

<style>
#student {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 15px;
}
</style>
