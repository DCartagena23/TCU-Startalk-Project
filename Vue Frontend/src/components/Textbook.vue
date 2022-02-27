<template>
   <main class="pb-8 pt-8">
      <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:max-w-7xl lg:px-8">
        <h1 class="sr-only">Chinese Textbook</h1>
        <!-- Main 3 column grid -->
        <div class="grid grid-cols-1 gap-4 items-start lg:grid-cols-3 lg:gap-8">
          <!-- Left column -->
          <div class="grid grid-cols-1 gap-4 lg:col-span-2">
            <section aria-labelledby="section-1-title">
              <h2 class="sr-only" id="section-1-title">Section title</h2>
              <div class="rounded-lg bg-white overflow-hidden shadow">
                <div class="p-6">
                  <!-- Your content -->
                  <Textbox :toggleEditButton="toggleEditButton" :toggleEditMode="toggleEditMode" :textbox="textbox" :goNextPage="goNextPage" :goPrevPage="goPrevPage" />
                </div>
              </div>
            </section>
          </div>

          <!-- Right column -->
          <!-- <div class="grid grid-cols-1 gap-4">
            <section aria-labelledby="section-2-title">
              <h2 class="sr-only" id="section-2-title">Textbook Navigation</h2>
              <div class="rounded-lg bg-white overflow-hidden shadow">
                <div class="p-6"> -->
                  <!-- Your content -->
                  <!-- <div id="side">
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
              <div class="card-body section">Section 1</div>
              <div class="card-body section">Section 2</div>
              <div class="card-body section">Section 3</div>
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
              <div class="card-body section">Section 1</div>
              <div class="card-body section">Section 2</div>
              <div class="card-body section">Section 3</div>
            </div>
          </div>
          <div class="card">
            <div class="card-header" id="headingThree">
              <h5 class="mb-0">
                <button
                  id="ch3"
                  class="btn btn collapsed section1"
                  v-on:click="toggleCollapse3"
                  data-toggle="collapse"
                  data-target="#collapseThree"
                  aria-expanded="false"
                  aria-controls="collapseThree"
                >
                  Chapter 3
                </button>
              </h5>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
              <div class="card-body section">Section 1</div>
              <div class="card-body section">Section 2</div>
              <div class="card-body section">Section 3</div>
            </div>
          </div>
        </div>
                  </div>
                </div>
              </div>
            </section>
          </div> -->
        </div>
      </div>
    </main>
</template>

<style scoped>
.section:hover {
  color: blue;
  cursor: pointer;
}
.section1:hover {
  cursor: pointer;
}
#side {
  text-align: center;
}
</style>

<script>
import Textbox from './Textbox.vue'

export default {
  name: 'Body',
  components: {
    Textbox,
  },
  data: function () {
    return {
      textbox: 'This did not register',
    }
  },
  props: {
    toggleEditButton: Boolean,
    toggleEditMode: Function,
  },
  methods: {
    goNextPage: function () {
      this.textbox = 'Next page'
    },
    goPrevPage: function () {
      this.textbox = 'Prev page'
    },
    toggleCollapse: function () {
      var chapter1 = document.getElementById('collapseOne')
      chapter1.classList.toggle('show')
    },
    toggleCollapse2: function () {
      var chapter2 = document.getElementById('collapseTwo')
      chapter2.classList.toggle('show')
    },
    toggleCollapse3: function () {
      var chapter3 = document.getElementById('collapseThree')
      chapter3.classList.toggle('show')
    },
  },
  mounted() {
    this.textbox =
      '据说，在中文里找不到一个跟英“privacy”相当的字。“隐私”这个词虽然有点儿像privacy，但是中国人不常说，而且“隐私”这个词，多少有些不好的意思，所以有人说中 国人没有privacy的观念。这话也许是对的。我的中国朋友常问我一些跟他们不相干的问 题，比方说：我一个月赚多少钱啊，有没有女朋友啊。在美国人看来，这完全是个人的事，谁也没有权利问这些问题。其实中国人觉得问这些问题并不是想发现什么秘密，而是平常谈话和表示关心的一种方式。我想，privacy并不是一个单纯的观念问题, 这跟社会制度与经济情况有一定的关系。在 中国，五、 六 个人挤在一间小屋里是常有的事儿，在这样的情况下很难保持个人隐私。再说，在同一个单位里，年龄、学历相同的人收入也差不多，所以许多是在美国是秘密的，在中国却是公开的当然也有一些事儿，在中国人看来是私事，美国人却把这些事儿当成平常的话题，像离婚、 收养孩子什么的。我的一些同学把中国人问人私事的习惯解释成好管闲事，其实，只要对中国社会多了解一些，就会发现许多事儿在我们看来是“私事”，在他们看来却不一定是“私事”。'
  },
}
</script>
