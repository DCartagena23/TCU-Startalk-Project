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
      <Assignment v-show="toggleAssignmentBool" />
      <Exam v-show="toggleExamBool" />
      <Grades v-show="toggleGradesBool" />
      <Forum v-show="toggleForumBool" />
      <Account v-show="toggleAccountBool" />
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
    },
    toggleAssignment: function () {
      this.toggleAssignmentBool = true
      this.toggleTextbookBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
    },
    toggleExam: function () {
      this.toggleExamBool = true
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
    },
    toggleGrades: function () {
      this.toggleGradesBool = true
      this.toggleExamBool = false
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleHomeBool = false
      this.toggleAccountBool = false
      this.toggleForumBool = false
    },
    toggleForum: function () {
      this.toggleForumBool = true
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
    },
    toggleHome: function () {
      this.toggleHomeBool = true
      this.toggleTextbookBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleAccountBool = false
      this.toggleGradesBool = false
    },
    toggleAccount: function () {
      this.toggleAccountBool = true
      this.toggleTextbookBool = false
      this.toggleHomeBool = false
      this.toggleAssignmentBool = false
      this.toggleExamBool = false
      this.toggleForumBool = false
      this.toggleGradesBool = false
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
