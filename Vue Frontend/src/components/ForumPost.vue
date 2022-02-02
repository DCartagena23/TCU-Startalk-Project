<template>
    <main v-show="postBool">
        <h1 class="text-3xl font-bold leading-tight text-gray-900" style="padding-top: 2.5rem;padding-bottom:2.5rem;">
            Sample Title
          </h1>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
          <!-- Replace with your content -->
          <div class="grid grid-cols-3">
            <div class="col-start-3 rounded-lg bg-indigo-600 text-white" style="padding-bottom:2.5rem; max-width:20rem;background-clip:content-box;">
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Similique in non expedita saepe veritatis officia ratione. Nihil commodi voluptas quidem, ratione aperiam aliquam. Sapiente doloremque similique repudiandae praesentium eveniet officia.
            </div>
            <div class="col-span-3" v-for="post in posts" :key="post.email" >
              <div class="rounded-lg bg-green-600 text-white" style="padding-bottom:2.5rem; max-width:20rem;background-clip:content-box;"> {{post.text}} </div>
            </div>
          </div>
          <input v-on:keyup.enter="submit" type="response" name="response" id="response" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="Press <enter> to submit " />
          <button type="button" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="goPrevPage" style="float: right; margin: 5px 5px 5px 0; padding-bottom:1rem;">Send Video</button>
          <button type="button" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" v-on:click="goPrevPage" style="float: right; margin: 5px 5px 5px 0; padding-bottom:1rem;">Send Audio</button>
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
    email: 'someone@something.com',
    text: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Similique in non expedita saepe veritatis officia ratione. Nihil commodi voluptas quidem, ratione aperiam aliquam. Sapiente doloremque similique repudiandae praesentium eveniet officia.',
    audio: '',
    video: '',
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
      submit: function(){
        var example = document.getElementById('response').value;
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
      }
    }
}
</script>