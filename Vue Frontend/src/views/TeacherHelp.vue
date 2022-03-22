<template>
<div>
    <Breadcrumb />
    <TeacherHeader />
    <div>
        <h1 class="py-6" style="text-align: center;">Teacher Help</h1>
        <div class="py-6" style="text-align: center;">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Pariatur ipsa voluptate repellat ratione. Dolore, repellat beatae magnam explicabo quam voluptates omnis molestias quae aliquid quaerat, distinctio, fugit nisi necessitatibus laborum!
        </div>
    </div>
</div> 
</template>

<style scoped>

</style>

<script>
import Breadcrumb from '@/components/Breadcrumb.vue'
import TeacherHeader from '@/components/TeacherHeader.vue'
export default {
    setup() {
        
    },
    components:{
        Breadcrumb,
        TeacherHeader,
    },
}
</script>