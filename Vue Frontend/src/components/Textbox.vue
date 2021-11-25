<template>
            <div class="col-8">
                <div id="textbook">
                    <div id="textbox">
                        {{textbox}}
                        <audio controls style="width:95%; margin:5px" id="sound1">
                            <source src="../assets/tts_test.mp3" type="audio/mp3" id="sound2">
                            Your browser does not support the audio element.
                        </audio>
                    </div>
                </div>
                <button type="button" class="btn btn-info" v-on:click="goPrevPage" style="float: left; margin: 5px 5px 5px 0;"> Previous Page</button>
                <button type="button" class="btn btn-info" v-on:click="toggleEditMode" v-show="toggleEditButton" style="margin: 5px 0 0 0">Edit Passage</button>
                <button type="button" class="btn btn-info" v-on:click="goNextPage" style="float: right; margin: 5px 0 5px 5px;"> Next Page</button>
            </div>
</template>

<style scoped>
#textbook{
    padding:25px;
    /* margin:25px; */
    text-align: center;
    border-width: 2px;
    border-style: solid;
}
</style>

<script>
export default {
    name:"Textbox",
    props:{
        toggleEditButton:Boolean,
        toggleEditMode:Function,
        textbox:String,
        goPrevPage:Function,
        goNextPage:Function
    }
}
</script>