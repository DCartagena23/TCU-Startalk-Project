<template>
    <b-col>
                <div class="accordion" role="tablist">
                    <b-card no-body class="mb-1">
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block v-b-toggle.accordion-1 variant="info">Chapter 1</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
                            <b-card-body>
                                <b-card-text>
                                    <div><div class="section"> Section 1</div></div>
                                    <div><div class="section"> Section 2</div></div>
                                    <div><div class="section"> Section 3</div></div>
                                </b-card-text>
                            </b-card-body>
                        </b-collapse>
                    </b-card>

                    <b-card no-body class="mb-1">
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block v-b-toggle.accordion-2 variant="info">Chapter 2</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
                            <b-card-body>
                                <b-card-text>
                                    <div><div class="section"> Section 1</div></div>
                                    <div><div class="section"> Section 2</div></div>
                                    <div><div class="section"> Section 3</div></div>
                                </b-card-text>
                             </b-card-body>
                        </b-collapse>
                    </b-card>

                    <b-card no-body class="mb-1">
                        <b-card-header header-tag="header" class="p-1" role="tab">
                            <b-button block v-b-toggle.accordion-3 variant="info">Chapter 3</b-button>
                        </b-card-header>
                        <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
                            <b-card-body>
                                <b-card-text>
                                   <div><div class="section"> Section 1</div></div>
                                   <div><div class="section"> Section 2</div></div>
                                   <div><div class="section"> Section 3</div></div>
                                </b-card-text>
                            </b-card-body>
                        </b-collapse>
                    </b-card>
                </div>
    </b-col>
</template>

<style scoped>
.section:hover{
    color: blue;
    cursor: pointer;
}
</style>

<script>
export default {
    name:'TextbookSidebar'
}
</script>