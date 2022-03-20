<!-- This example requires Tailwind CSS v2.0+ -->
<template>
<div>
<Breadcrumb />
<Header />
<div class="py-10" v-show="forumBool">
  <header style="padding-bottom: 2.5em;">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 class="text-3xl font-bold leading-tight text-gray-900" style="text-align:center;">
            Forums
          </h1>
          <div style="text-align: center">
            <button type="button" class="px-6
      py-2.5
      bg-indigo-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-indigo-700 hover:shadow-lg
      focus:bg-indigo-600 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-indigo-800 active:shadow-lg
      transition
      duration-150
      ease-in-out" style="align-self:center;" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Create forum
            </button>
          </div>

<!-- Modal -->
<div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto"
  id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog relative w-auto pointer-events-none">
    <div
      class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white bg-clip-padding rounded-md outline-none text-current">
      <div
        class="modal-header flex flex-shrink-0 items-center justify-between p-4 border-b border-gray-200 rounded-t-md">
        <h5 class="text-xl font-medium leading-normal text-gray-800" id="exampleModalLabel">Create post</h5>
        <button type="button"
          class="btn-close box-content w-4 h-4 p-1 text-black border-none rounded-none opacity-50 focus:shadow-none focus:outline-none focus:opacity-100 hover:text-black hover:opacity-75 hover:no-underline"
          data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body relative p-4">
          <div class="border border-gray-300 rounded-md px-3 py-2 shadow-sm focus-within:ring-1 focus-within:ring-indigo-600 focus-within:border-indigo-600">
            <label for="title" class="block text-xs font-medium text-gray-900">Title</label>
            <input type="text" name="title" id="forumTitle" class="block w-full border-0 p-0 text-gray-900 placeholder-gray-500 focus:ring-0 sm:text-sm" placeholder="Example Forum" />
          </div>
      </div>
      <div class="modal-body relative p-4">
          <div class="border border-gray-300 rounded-md px-3 py-2 shadow-sm focus-within:ring-1 focus-within:ring-indigo-600 focus-within:border-indigo-600">
            <label for="description" class="block text-xs font-medium text-gray-900">Description</label>
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
      id="forumArea"
      rows="3"
      placeholder="Enter description here"
    ></textarea>
          </div>
      </div>
      <div
        class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end p-4 border-t border-gray-200 rounded-b-md">
        <button type="button" class="px-6
          py-2.5
          bg-purple-600
          text-white
          font-medium
          text-xs
          leading-tight
          uppercase
          rounded
          shadow-md
          hover:bg-purple-700 hover:shadow-lg
          focus:bg-purple-700 focus:shadow-lg focus:outline-none focus:ring-0
          active:bg-purple-800 active:shadow-lg
          transition
          duration-150
          ease-in-out" data-bs-dismiss="modal">Close</button>
        <button type="button" class="px-6
      py-2.5
      bg-blue-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-blue-700 hover:shadow-lg
      focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-blue-800 active:shadow-lg
      transition
      duration-150
      ease-in-out
      ml-1" data-bs-dismiss="modal" v-on:click="createPost()">Save changes</button>
      </div>
    </div>
  </div>
</div>
        </div>
      </header>
        <main>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
          <!-- Replace with your content -->
          <div class="flex flex-col">
    <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
      <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
        <div class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
              <tr>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Title
                </th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Author
                </th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Status
                </th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Role
                </th>
                <th scope="col" class="relative px-6 py-3">
                  <span class="sr-only">Edit</span>
                </th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="post in posts" :key="post.email">
                <td v-on:click="toggleForumPost(post.id)" id="toForumBoard" class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 text-left">
                  {{ post.title }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-left">
                  {{ post.author }}
                </td>
               <td class="px-6 py-4 whitespace-nowrap text-left">
                  <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                    Active
                  </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-left">
                  {{ post.role }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                  <a href="#" class="text-indigo-600 hover:text-indigo-900" v-show="toggleEditButton">Edit</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
          </div>
          <!-- /End replace -->
        </div>
      </main>
</div>
</div>
</template>

<style scoped>
#toForumBoard {
  cursor: pointer;
}
</style>

<script>
import Breadcrumb from '@/components/Breadcrumb.vue'
import Header from '@/components/Header.vue'
const posts = [
  {
    author: 'Jane Cooper',
    description: 'Sample description',
    id: '',
    messages: [],
    title: 'Post 1',
  },
  // More people...
]


export default {
  setup() {

    return {
      posts,
    }
  },
  components: {
    Breadcrumb,
    Header
  },
  data: function(){
    return{
      forumBool:true,
    }
  },
  methods:{
    createPost: function(){
      var forumTitle = document.getElementById('forumTitle').value;
      var forumArea = document.getElementById('forumArea').value;
      // alert(forumArea);
      var newPost = {
        author: 'John Doe',
        title: forumTitle,
        description: forumArea,
        messages: [],
        id: this.objectId(),
      };
      posts.push(newPost);
      this.addForum();
      this.forumBool = false;
      this.forumBool = true;
    },
    toggleForumPost(id){
      this.$router.push({ path: `/post/${id}` })
    },
     async getAllForums(){
      const { data: res } = await this.$http.get(`/forums/findAll`)
      if (res.status == 200) {
        console.log(res.data)
      }
      while (posts.length > 0){
        posts.pop()
      }
      res.data.forEach((post) => {
        var newPost = {
          author: post.author,
          title: post.title,
          description: post.desc,
          role: 'Admin',
          id: post.id,
          messages: post.messages
        }
        posts.push(newPost);
      })
      // var forumArea = document.getElementById('forumArea').value;
      this.forumBool = false;
      this.forumBool = true;
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
  async addForum(){
    console.log(posts[posts.length-1])
    const { data: res } = await this.$http.post(`/forums/saveForum`,posts[posts.length-1])
    console.log(res.data)
  },
  },
  props: {
    toggleEditButton: Boolean
  },
  mounted(){
    this.getAllForums()
  }
}
</script>
© 2022 Tailwind Labs Inc. All rights reserved.

Privacy Policy