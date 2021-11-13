<template>
    <div>
        <TextboxHead />
        <b-row>
            <TextbookSidebar />
            <Textbox :toggleEditButton="toggleEditButton" />
            <b-col></b-col>
        </b-row>
    </div>
    
</template>

<style scoped>
</style>

<script>
import TextboxHead from "../components/TextboxHead.vue"
import Textbox from "../components/Textbox.vue"
import TextbookSidebar from "../components/TextbookSidebar.vue"

export default {
    name: 'Body',
    components:{
        TextboxHead,
        Textbox,
        TextbookSidebar
    },
    props:{
        toggleEditButton:Boolean
    },
}
</script>