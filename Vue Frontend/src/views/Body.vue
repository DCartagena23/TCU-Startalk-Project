<template>
    <div>
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
import Textbox from "../components/Textbox.vue"
import TextbookSidebar from "../components/TextbookSidebar.vue"

export default {
    name: 'Body',
    components:{
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
        this.textbox = "据说，在中文里找不到一个跟英“privacy”相当的字。“隐私”这个词虽然有点儿像privacy，但是中国人不常说，而且“隐私”这个词，多少有些不好的意思，所以有人说中 国人没有privacy的观念。这话也许是对的。我的中国朋友常问我一些跟他们不相干的问 题，比方说：我一个月赚多少钱啊，有没有女朋友啊。在美国人看来，这完全是个人的事，谁也没有权利问这些问题。其实中国人觉得问这些问题并不是想发现什么秘密，而是平常谈话和表示关心的一种方式。我想，privacy并不是一个单纯的观念问题, 这跟社会制度与经济情况有一定的关系。在 中国，五、 六 个人挤在一间小屋里是常有的事儿，在这样的情况下很难保持个人隐私。再说，在同一个单位里，年龄、学历相同的人收入也差不多，所以许多是在美国是秘密的，在中国却是公开的当然也有一些事儿，在中国人看来是私事，美国人却把这些事儿当成平常的话题，像离婚、 收养孩子什么的。我的一些同学把中国人问人私事的习惯解释成好管闲事，其实，只要对中国社会多了解一些，就会发现许多事儿在我们看来是“私事”，在他们看来却不一定是“私事”。";
    }

}
</script>