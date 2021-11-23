<template>
    <div>
        <TextboxHead />
        <div class="row">
            <TextbookSidebar />
            <Textbox :toggleEditButton="toggleEditButton" :toggleEditMode="toggleEditMode"/>
            <div class="col"></div>
        </div>
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
        toggleEditButton:Boolean,
        toggleEditMode:Function
    },
}
</script>