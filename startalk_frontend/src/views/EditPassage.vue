<template>
    <b-row>
        Edit passage
        <b-button variant="info" v-on:click="toggleEditChapter">Edit Chapter</b-button>
    </b-row>
</template>

<style scoped>

</style>

<script>
export default {
    name:"EditPassage",
    props:{
        toggleEditChapter: Function
    }
}
</script>