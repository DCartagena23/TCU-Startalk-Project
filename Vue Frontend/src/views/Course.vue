<template>
<Breadcrumb/>
<Book/>
<AudioTest/>
</template>

<script>
// @ is an alias to /src
import Book from '@/components/Book'
import AudioTest from '@/components/AudioTest'
import Breadcrumb from '@/components/Breadcrumb.vue'
export default {
  name: 'Course',
  components: {
    Book,
    AudioTest,
    Breadcrumb
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
