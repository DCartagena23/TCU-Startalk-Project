<template>
  <div class="py-10">
      <header style="padding-bottom: 2.5em;">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 class="text-3xl font-bold leading-tight text-gray-900">
            Grades
          </h1>
          <button type="button" v-show="toggleEditButton" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
              Add/Edit Grades
           </button>
        </div>
      </header>
      <main>
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
          <!-- Replace with your content -->
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
                  Name
                </th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Score
                </th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="grade in grades" :key="grade.email">
                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 text-left">
                  {{ grade.title }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 text-left">
                  {{ grade.score }}
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
          <!-- /End replace -->
        </div>
      </main>
    </div>
</template>

<style scoped></style>

<script>
const grades = [
  {
    title: 'Assignment 1',
    email: 'you@example.com',
    score: 100,
  }
]

export default {
  name: 'Grades',
  data: function(){
    return{
      grades,
    }
  },
  props:{
    toggleEditButton:Boolean,
  }
}
</script>
