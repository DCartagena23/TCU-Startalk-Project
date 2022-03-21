<template>
<div>
<Breadcrumb/>
<TeacherHeader />
<Book/>
<AudioTest/>
</div>
</template>

<script>
// @ is an alias to /src
import Book from '@/components/Book'
import AudioTest from '@/components/AudioTest'
import Breadcrumb from '@/components/Breadcrumb.vue'
import TeacherHeader from '@/components/TeacherHeader.vue'
export default {
  name: 'Course',
  components: {
    Book,
    AudioTest,
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
