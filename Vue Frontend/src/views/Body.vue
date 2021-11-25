<template>
    <div>
        <TextboxHead />
        <div class="row">
            <TextbookSidebar />
            <Textbox :toggleEditButton="toggleEditButton"
                :toggleEditMode="toggleEditMode" :textbox="textbox"
                :goNextPage="goNextPage" :goPrevPage="goPrevPage"/>
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
    data:function(){
        return{
            textbox:"This did not register"
        }
    },
    props:{
        toggleEditButton:Boolean,
        toggleEditMode:Function,
    },
    methods:{
        goNextPage: function(){
            this.textbox = "Next page"
        },
        goPrevPage: function(){
            this.textbox = "Prev page"
        }
    },
    mounted(){
        this.textbox = "隐私'这个词虽然有点儿像privacy，但是中国人不常说，而且“隐私”这个词，多少有些不好的意思，所以有人说中 国人没有privacy的观念";
    }

}
</script>