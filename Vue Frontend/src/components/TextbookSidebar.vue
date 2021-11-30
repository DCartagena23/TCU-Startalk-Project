<template>
<div class="col">
  <div id="accordion">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button id="ch1" class="btn btn section1" v-on:click="toggleCollapse" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Chapter 1
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body section">
        Section 1
      </div>
      <div class="card-body section">
        Section 2
      </div>
      <div class="card-body section">
        Section 3
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h5 class="mb-0">
        <button id="ch2" class="btn btn collapsed section1" v-on:click="toggleCollapse2" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Chapter 2
        </button>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
      <div class="card-body section">
        Section 1
      </div>
      <div class="card-body section">
        Section 2
      </div>
      <div class="card-body section">
        Section 3
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button id="ch3" class="btn btn collapsed section1" v-on:click="toggleCollapse3" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Chapter 3
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body section">
        Section 1
      </div>
      <div class="card-body section">
        Section 2
      </div>
      <div class="card-body section">
        Section 3
      </div>
    </div>
  </div>
</div>
</div>

</template>

<style scoped>
.section:hover{
    color: blue;
    cursor: pointer;
}
.section1:hover{
  cursor: pointer;
}
</style>

<script>
export default {
    name:'TextbookSidebar',
    methods:{
      toggleCollapse:function(){
        var chapter1 = document.getElementById('collapseOne');
        chapter1.classList.toggle('show');
        },
      toggleCollapse2:function(){
        var chapter2 = document.getElementById('collapseTwo');
        chapter2.classList.toggle('show');
      },
      toggleCollapse3:function(){
        var chapter3 = document.getElementById('collapseThree');
        chapter3.classList.toggle('show');
      }
    }
}
</script>