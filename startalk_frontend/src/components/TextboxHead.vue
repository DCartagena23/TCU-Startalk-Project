<template>
    <b-row  style="margin: 10px 0 10px 0">
            <b-col></b-col>
            <b-col cols="3">
                <div style="float: left;">Chapter 1: Page 1</div>
                <div style="float: right;"> 
                    <b-form-checkbox v-model="checked" name="check-button" switch>
                        Pinyin
                    </b-form-checkbox>
                </div>
            </b-col>
            <b-col></b-col>
        </b-row>
</template>

<style scoped>

</style>

<script>
export default {
    name: "TextboxHead"
}
</script>