<template>
<div>
<Breadcrumb/>
<TeacherHeader />
<h6 class="display-3 py-6" style="text-align:center;">Course: {{title}}</h6>
<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Book</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Homework</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#board" type="button" role="tab" aria-controls="profile" aria-selected="false">Forums</button>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab"><Book/></div>
  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab"><Homework/></div>
    <div class="tab-pane fade" id="board" role="tabpanel" aria-labelledby="board-tab"><BoardModule/></div>
</div>
</div>
</template>

<script>
// @ is an alias to /src
import Book from '@/components/Book'
import Homework from '@/components/Homework'
import BoardModule from '@/components/BoardModule'
import Breadcrumb from '@/components/Breadcrumb.vue'
import TeacherHeader from '@/components/TeacherHeader.vue'
export default {
  name: 'Course',
  components: {
    Book,
    Homework,
    Breadcrumb,
    TeacherHeader,
    BoardModule
  },
  data: function () {
    return {
      title:"",
    }
  },
  mounted: function () {
    this.getName(this.$route.params.courseId)
  },
  methods: {
        async getName(id) {
      const { data: res } = await this.$http.get(`/courses/getName/${id}`)
      if (res.status == 200) {
        this.title = res.data
      }
    },

  },
}
</script>

<style scoped></style>
>
