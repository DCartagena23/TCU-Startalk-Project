<template>
<div>
<Breadcrumb/>
<TeacherHeader />
<Book/>
<Homework/>
</div>
</template>

<script>
// @ is an alias to /src
import Book from '@/components/Book'
import Homework from '@/components/Homework'
import Breadcrumb from '@/components/Breadcrumb.vue'
import TeacherHeader from '@/components/TeacherHeader.vue'
export default {
  name: 'Course',
  components: {
    Book,
    Homework,
    Breadcrumb,
    TeacherHeader,
  },
  data: function () {
    return {

    }
  },
  mounted: function () {

  },
  methods: {

  },
}
</script>

<style scoped></style>
>
