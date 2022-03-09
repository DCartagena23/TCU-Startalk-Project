<template>
 <ul class="breadcrumb">
  <li><a href="#" @click.prevent="goHome">Course</a></li>
  <li v-if="book"><a href="#" @click.prevent="goBook">Book</a></li>
  <li v-if="chapter"><a href="#" @click.prevent="goChapter">Chapter</a></li>
  <li v-if="student"><a href="#" @click.prevent="goStudent">Read</a></li>
  <li v-if="read"><a href="#" @click.prevent="goRead">Read (Teacher)</a></li>
  <li v-if="edit"><a href="#" @click.prevent="goEdit">Edit</a></li>  
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
      book: false,
      chapter: false,
      student: false,
      read: false,
      edit: false,
    }
  },
  created(){
    if (this.path == "book"){
      this.book = true
    }
    if (this.path == "chapter"){
      this.book = true
      this.chapter = true
    }
    if (this.path == "student"){
      this.book = true
      this.chapter = true
      this.student = true
    }
    if (this.path == "read"){
      this.book = true
      this.chapter = true
      this.read = true
    }
    if (this.path == "edit"){
      this.book = true
      this.chapter = true
      this.read = true
      this.edit = true
    }
  },
  methods: {
    goHome(){
      //this.$router.push({ path: `/read/${this.$route.params.courseId}/${this.$route.params.bookId}/${this.$route.params.id}` })
      this.$router.push({ path: `/home` })
    },
    goBook(){
      this.$router.push({ path: `/book/${this.$route.params.courseId}` })      
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
  }
}
</script>
