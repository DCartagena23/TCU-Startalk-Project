<template>
<div class="py-10">
    <h1 class="text-3xl font-bold leading-tight text-gray-900">
    Sample Assignment Name
    </h1>
    <h4 class="py-6">Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor iure saepe deleniti eum mollitia ipsa odio nostrum deserunt modi placeat sunt vel, rerum quasi maxime illo at repellat dolores temporibus!</h4>
        <input type="file" id="fileSelect" name="fileSelect" style="margin: 1.5rem 0 1.5rem 7rem">
     <form id="assignmentForm">
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
      placeholder="Type to add a comment"
            ></textarea>
            <!-- <button id="postButton" v-on:click="submitFunction(event)">check</button> -->
          </form>
    <button type="button" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 1.5rem 0 1.5rem 0">
        Submit Assignment
    </button>
</div>
</template>

<style scoped>

</style>

<script>
export default {
    setup() {

    },
}
</script>