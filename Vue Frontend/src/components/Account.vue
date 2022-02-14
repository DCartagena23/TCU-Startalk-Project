<template>
  <div class="py-10">
      <header style="padding-bottom: 2.5em;">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 class="text-3xl font-bold leading-tight text-gray-900">
            Account Information
          </h1>
        </div>
      </header>
      <div class="grid grid-cols-3">
        <div class="col-start-2">
                  <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
          <div class="mt-1">
        <input type="name" name="name" id="name" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="Jane Cooper" />
    </div>
      <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
    <div class="mt-1">
      <input type="email" name="email" id="email" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="you@example.com" />
    </div>
     <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
    <div class="mt-1" style="padding-bottom:1rem">
      <input type="password" name="password" id="password" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="123456" />
    </div>
    <button id="postButton" class=" inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" style="margin: 5px 5px 5px 0;display: inline;">Save Information</button>
        </div>
      </div>
    </div>
</template>

<style scoped></style>

<script>
export default {
  name: 'Account',
}
</script>
