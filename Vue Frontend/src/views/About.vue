<template>
<div class="container">
    <div class="center" style="text-align: center; font-size: 2em;">
        <a href="/">Bookstore</a> | <a href="/about">About</a>
    </div>
  <div>{{message}}</div>
</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'About',
  data: function () {
    return {
      message:"Some random statements."
    }
  },  
  created() {
    this.message = this.$route.params.id;
    if (this.message == undefined){
      this.message = "Some random statements."
    }
  },
}
</script>

