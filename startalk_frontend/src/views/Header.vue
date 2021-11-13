<template>
    <b-row>
        <b-col>
                <Logo :toggleHome="toggleHome" />
        </b-col>
        <b-col cols="5">
            <div>
                <!-- <div class="nav" v-on:click="toggleAccount">Textbook</div>
                <div class="nav" v-on:click="toggleAccount">Assignment</div>
                <div class="nav" v-on:click="toggleAccount">Exam</div>
                <div class="nav" v-on:click="toggleAccount">Forum</div> -->
                <NavButton title="Textbook" :toggleFunction="toggleTextbook" />
                <NavButton title="Assignment" :toggleFunction="toggleAssignment" />
                <NavButton title="Exam" :toggleFunction="toggleExam"/>
                <NavButton title="Forum" :toggleFunction="toggleForum"/>
            </div>
        </b-col>
        <b-col>
            <div>
                <!-- <div class="nav2" v-show="toggle"> Account </div> -->
                <AccountDrop :toggleEdit="toggleEdit" :toggleAccount="toggleAccount" :accountMode="accountMode" />
            </div>
        </b-col>
    </b-row>
</template>

<style scoped>

.nav:hover{
    color:blue;
    text-decoration: none;
}

.nav{
    /* border-style:solid;
    border-width:0 1px 0 1px; */
    float: left;
    font-size: 20px;
    margin: 18px;
}

.nav2{
    /* border-style:solid;
    border-width:0 1px 0 1px; */
    float: right;
    font-size: 20px;
    margin: 18px;
}

.logo{
    height:50%;
    width:50%;
    float: left;
    /* margin-left: auto;
    margin-right: auto;
    display: block; */
}

#acclogo{
    height:15%;
    width:15%;
    margin-left: auto;
    margin-right: auto;
    display: block;
}
</style>

<script>
import NavButton from '../components/NavButton.vue'
import AccountDrop from '../components/AccountDrop.vue'
import Logo from "../components/Logo.vue"

export default {
    name: 'Header',
    components:{
        NavButton,
        AccountDrop,
        Logo
    },
    props:{
        toggleEdit:Function,
        toggleTextbook:Function,
        toggleAssignment:Function,
        toggleExam:Function,
        toggleForum:Function,
        toggleHome:Function,
        toggleAccount:Function,
        accountMode:String
    }
}
</script>