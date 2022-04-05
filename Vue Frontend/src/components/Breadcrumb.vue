<template>
 <ul class="breadcrumb">
  <li><a href="#" @click.prevent="goHome">Home</a></li>
  <li v-if="course"><a href="#" @click.prevent="goCourse">Course</a></li>
  <li v-if="chapter"><a href="#" @click.prevent="goChapter">Book</a></li>
  <li v-if="student"><a href="#" @click.prevent="goStudent">Read</a></li>
  <li v-if="read"><a href="#" @click.prevent="goRead">Read (Teacher)</a></li>
  <li v-if="edit"><a href="#" @click.prevent="goEdit">Edit</a></li>  
  <li v-if="homework"><a href="#" @click.prevent="goHomework">Homework</a></li>
  <li v-if="test"><a href="#" @click.prevent="goTest">Test</a></li>
  <li v-if="answer"><a href="#" @click.prevent="goAnswer">View Answer</a></li>
  <li v-if="comment"><a href="#" @click.prevent="goComment">Comment</a></li>
  <li v-if="board"><a href="#" @click.prevent="goBoard">Board</a></li>
  <li v-if="forum"><a href="#" @click.prevent="goForum">Forum</a></li>
  <li v-if="post"><a href="#" @click.prevent="goPost">Post</a></li>
</ul> 
</template>

<style scoped>
ul.breadcrumb {
  padding: 10px 16px;
  list-style: none;
  background-color: #eee;
} 

/* Display list items side by side */
ul.breadcrumb li {
  display: inline;
  font-size: 18px;
}

/* Add a slash symbol (/) before/behind each list item */
ul.breadcrumb li+li:before {
  padding: 8px;
  color: black;
  content: "/\00a0";
}

/* Add a color to all links inside the list */
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}

/* Add a color on mouse-over */
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
</style>

<script>
export default {
  name: 'Breadcrumb',
  data: function () {
    return {
      path: this.$route.path.split("/")[1],
      course: false,
      book: false,
      chapter: false,
      student: false,
      read: false,
      edit: false,
      test: false,
      answer: false,
      comment: false,
      homework: false,
      board: false,
      forum: false,
      post: false,
    }
  },
  created(){
    if (this.path == "course"){
      this.course = true
    }
    if (this.path == "chapter"){
      this.course = true
      this.chapter = true
    }
    if (this.path == "student"){
      this.course = true
      this.chapter = true
      this.student = true
    }
    if (this.path == "read"){
      this.course = true
      this.chapter = true
      this.read = true
    }
    if (this.path == "edit"){
      this.course = true
      this.chapter = true
      this.read = true
      this.edit = true
    }
    if (this.path == "audioTest"){
      this.course = true
      this.homework = true
    }
    if (this.path == "audioTestTaking"){
      this.course = true
      this.homework = true
      this.test = true
    }
    if (this.path == "audioAnswerList"){
      this.course = true
      this.homework = true
      this.test = true
      this.answer = true
    }
    if (this.path == "audioAnswerComment"){
      this.course = true
      this.homework = true
      this.test = true
      this.answer = true
      this.comment = true
    }
    if (this.path == "board"){
      this.course = true
      this.board = true
    }
    if (this.path == "forum"){
      this.course = true
      this.board = true
      this.forum= true
    }
    if (this.path == "post"){
      this.course = true
      this.board = true
      this.forum = true
      this.post = true
    }
  },
  methods: {
    goHome(){
      //this.$router.push({ path: `/read/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.$route.params.id}` })
      this.$router.push({ path: `/home` })
    },
    goCourse(){
      this.$router.push({ path: `/course/${this.$route.params.courseId}` })      
    },
    goChapter(){
      this.$router.push({ path: `/chapter/${this.$route.params.courseId}/${this.$route.params.bookId}` })
    },
    goStudent(){
      this.$router.push({ path: `/student/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.$route.params.id}` })
    },
    goRead(){
      this.$router.push({ path: `/read/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.$route.params.id}` })
    },
    goEdit(){
      this.$router.push({ path: `/edit/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.$route.params.id}` })
    },
    goHomework(){
      this.$router.push({ path: `/audioTest/${this.$route.params.courseId}/${this.$route.params.homeworkId}` })   
    },
    goTest(){
      this.$router.push({ path: `/audioTestTaking/${this.$route.params.courseId}/${this.$route.params.homeworkId}/${this.$route.params.id}` })   
    },
    goAnswer(){
      this.$router.push({ path: `/audioAnswerList/${this.$route.params.courseId}/${this.$route.params.homeworkId}/${this.$route.params.id}` })   
    },
    goComment(){
      this.$router.push({ path: `/audioAnswerComment/${this.$route.params.courseId}/${this.$route.params.homeworkId}/${this.$route.params.id}/${this.$route.params.answerId}` })   
    },
    goBoard(){
      this.$router.push({ path: `/board/${this.$route.params.courseId}` }) 
    },
    goForum(){
      this.$router.push({ path: `/forum/${this.$route.params.courseId}/${this.$route.params.boardId}` }) 
    },
    goPost(){
      this.$router.push({ path: `/post/${this.$route.params.courseId}/${this.$route.params.boardId}/${this.$route.params.forumId}` }) 
    }
  }
}
</script>
