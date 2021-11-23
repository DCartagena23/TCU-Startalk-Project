<template>
    <div>
        <img src="../assets/startalk.png" alt="website logo" class="logo" v-on:click="toggleHome">
    </div>
</template>

<style scoped>
.logo{
    height:50%;
    width:50%;
    float: left;
    cursor: pointer;
    /* margin-left: auto;
    margin-right: auto;
    display: block; */
}
</style>

<script>
export default {
    name: "Logo",
    props:{
        toggleHome:Function
    }
}
</script>