<template>
  <div>
    <div class="cont_right2 round">
      <div class="board_wrap">
        <div class="cont_title">{{$t("menu.serviceCenterSubmenu.faq")}}
          <div class="board_serch">
            <div class="search_text">
              <input type="search" v-model="schWord" v-bind:placeholder='$t("description.inputKeyword")'></div>
            <a class="serch_btn" href="#" @click="getSearchList(schWord)">{{ $t("button.search") }}</a>
          </div>
        </div>

        <div class="faqWrap">
          <div>
            <template v-for="item in searchRank">
              <div>
              <div class="title pt10">{{ item.freqQueSectnNm }}</div>
                <ul>
                <template v-for="faqList in item.descList">
                    <li>
                      <a class="fff off"
                         v-on:click="$event.target.className == 'fff off' ? $event.target.className = 'fff on' : $event.target.className = 'fff off'">{{ faqList.queNm }}</a>
                      <div class="aaa" v-html="faqList.anserCtnt">{{ faqList.anserCtnt }}</div>
                    </li>
                </template>
                </ul>
              </div>
            </template>
              </div>

        </div>


      </div>
    </div>
    <div style='clear:both;'></div>
  </div>
</template>


<script>


  export default {
    name:"faq",
    data() {
      return {
        activeIndex:0,
        searchRank :[],
        schWord:'',
        searchList:[],
        search:'',
        listSize:'',
        anserCtnt:''
      }
    },
    methods: {
      getSearchList: function(schWord){
        this.$http.get('/api/v1/faq/searchFaq?schWord='+schWord).then((res)=>{
          this.searchRank = res.data.descList;
          this.listSize = this.searchRank.size;
        /*  let idx = this.searchRank.length;
          for(let i = 0; i < idx; i++){
            if(!this.searchRank[i].descList.length) {
              this.searchRank[i].splice(i);
            }
          }*/
        })
      }
    },
    created() {
      this.$http.get('/api/v1/faq/getFaqList').then((res)=>{
        this.searchRank = res.data.descList;
        let index = this.searchRank.length;

        for(let i =0 ; i<index; i++){
          let idx = this.searchRank[i].descList.length;
          for(let j =0; j<idx; j++){
            this.searchRank[i].descList[j].anserCtnt = this.searchRank[i].descList[j].anserCtnt.split('\n').join('<br/>');
          }
        }
      })


    }
  }

</script>
<style scoped>
</style>
