<template>
<div>
  <Breadcrumb />
  <Header />
    <main v-show="postBool">
        <h1 class="text-3xl font-bold leading-tight text-gray-900" style="padding-top: 2.5rem;padding-bottom:2.5rem;text-align: center;">
            Sample Title
          </h1>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
          <!-- Replace with your content -->
          <div class="grid" id="forumSection" style="grid-template-columns: repeat(3, 1fr);">
            <!-- <div class="col-start-3 rounded-lg bg-indigo-600 text-white" style="margin-bottom:2.5rem; max-width:20rem;">
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Similique in non expedita saepe veritatis officia ratione. Nihil commodi voluptas quidem, ratione aperiam aliquam. Sapiente doloremque similique repudiandae praesentium eveniet officia.
            </div>
            <div class="col-span-3" v-for="post in posts" :key="post.email" >
              <div class="rounded-lg bg-green-600 text-white" style="margin-bottom:2.5rem; max-width:20rem;"> {{post.text}} </div>
            </div> -->
          </div>
          <!-- <input v-on:keyup.enter="submit" type="response" name="response" id="response" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="Press <enter> to submit " /> -->
          <form id="postForm">
            <textarea
      class="
        form-control
        block
        w-full
        px-3
        py-1.5
        text-base
        font-normal
        text-gray-700
        bg-white bg-clip-padding
        border border-solid border-gray-300
        rounded
        transition
        ease-in-out
        m-0
        focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none
      "
      id="response"
      rows="3"
      placeholder="Press <Send Text> to submit written response"
            ></textarea>
            <!-- <button id="postButton" v-on:click="submitFunction(event)">check</button> -->
          </form>
          <div class="grid grid-cols-10">
            <button id="postButton" class="col-start-10 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="submitFunction()" style="margin: 5px 5px 5px 0;display: inline;">Send Text</button>
            <!-- <button type="button" class="col-start-9 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 5px 5px 0;display: inline;">Send Video</button>
            <button type="button" class="col-start-10 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 5px 5px 0; display: inline;">Send Audio</button> -->
          </div>
          <!-- /End replace -->
        </div>
      </main>
</div>
</template>

<style scoped>

</style>

<script>
import Breadcrumb from '@/components/Breadcrumb.vue'
import Header from '@/components/Header.vue'
const posts = [
  {
    name: '',
    id: 0,
    text: '',
  },
]
export default {
    setup() {

    },
    components:{
      Breadcrumb,
      Header,
    },
    data: function(){
      return{
        postBool:true,
        posts,
      }
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
  },
    methods: {
      submitFunction: function(){
        var example = document.getElementById('response').value;
        var forumSection = document.getElementById('forumSection');
        // alert(example);
        const newPost = {
          name: 'Jane Doe',
          id: this.objectId(),
          text: example,
        }
        posts.push(newPost);
        var post = document.createElement('div');
        post.innerHTML = newPost.name + ': ' + newPost.text;
        if(post.id == 1){
          document.write('<br>');
          post.style.backgroundColor = 'rgba(79,20,229)';
          post.style.gridColumnStart = '3';
        }
        else{
          post.style.backgroundColor = 'rgba(5,150,105)';
          post.style.gridColumnStart = '1';
        }
        post.style.borderRadius = '.5rem';
        post.style.color = 'white';
        post.style.marginBottom = '2.5rem';
        post.style.maxWidth = '20rem';
        forumSection.appendChild(post);
        this.postBool = false;
        this.postBool = true;
        document.getElementById('response').value = '';
      },
      async getMessages(id){
      const { data: res } = await this.$http.get(`/forums/findOne/${id}`)
      if (res.status == 200) {
        console.log(res.data)
      }
      while(posts.length>0){
        posts.pop()
      }
      console.log(posts)
      res.data.messages.forEach((post) => {
        var newPost = {
          name: post.user.username,
          id: post.id,
          text: post.content,
        }
        console.log(newPost)
        posts.push(newPost)
        console.log(posts)
      })
      },
      addMessages: function(){
        posts.forEach((post) => {
          var forumSection = document.getElementById('forumSection');
          var newPost = document.createElement('div');
          newPost.innerHTML = post.name + ': ' + post.text;
          newPost.style.borderRadius = '.5rem';
          if(post.name == this.currentUser.username){
            newPost.style.backgroundColor = 'rgba(79,20,229)';
            newPost.style.gridColumnStart = '3';
          }
          else{
            newPost.style.backgroundColor = 'rgba(5,150,105)';
            newPost.style.gridColumnStart = '1';
          }
          newPost.style.color = 'white';
          newPost.style.marginBottom = '2.5rem';
          newPost.style.maxWidth = '20rem';
          forumSection.appendChild(newPost);
        })
        this.postBool = false;
        this.postBool = true;
      },
      objectId() {
      var timestamp = ((new Date().getTime() / 1000) | 0).toString(16)
      return (
        timestamp +
        'xxxxxxxxxxxxxxxx'
          .replace(/[x]/g, function () {
            return ((Math.random() * 16) | 0).toString(16)
          })
          .toLowerCase()
      )
    },
    },
    mounted(){
      this.getMessages(this.$route.params.forumId);
      this.addMessages();
    }
}
</script>