<template>
    <main v-show="postBool">
        <h1 class="text-3xl font-bold leading-tight text-gray-900" style="padding-top: 2.5rem;padding-bottom:2.5rem;">
            Sample Title
          </h1>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
          <!-- Replace with your content -->
          <div class="grid grid-cols-3" id="forumSection">
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
            <button id="postButton" class="col-start-8 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="submitFunction()" style="margin: 5px 5px 5px 0;display: inline;">Send Text</button>
            <button type="button" class="col-start-9 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 5px 5px 0;display: inline;">Send Video</button>
            <button type="button" class="col-start-10 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 5px 5px 0; display: inline;">Send Audio</button>
          </div>
          <!-- /End replace -->
        </div>
      </main>
</template>

<style scoped>

</style>

<script>

const posts = [
  {
    name: 'John Doe',
    id: 0,
    text: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Similique in non expedita saepe veritatis officia ratione. Nihil commodi voluptas quidem, ratione aperiam aliquam. Sapiente doloremque similique repudiandae praesentium eveniet officia.',
  },
]

export default {
    setup() {
        
    },
    data: function(){
      return{
        postBool:true,
        posts,
      }
    },
    methods: {
      submitFunction: function(){
        var example = document.getElementById('response').value;
        // alert(example);
        const newPost = {
          name: 'Jane Doe',
          email: 'someone1@something.com',
          text: example,
          audio: '',
          video: '',
        }
        posts.push(newPost);
        this.postBool = false;
        this.postBool = true;
        document.getElementById('response').value = '';
      }
    },
    mounted(){
      const forumSection = document.getElementById('forumSection');
      var post = document.createElement('div');
      post.innerHTML = posts[0].text;
      post.style.gridColumnStart = '3';
      post.style.borderRadius = '.5rem';
      post.style.backgroundColor = 'rgba(79,20,229)';
      post.style.color = 'white';
      post.style.marginBottom = '2.5rem';
      post.style.maxWidth = '20rem';
      forumSection.appendChild(post);
    }
}
</script>