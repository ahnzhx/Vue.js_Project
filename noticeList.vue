<template>
  <div>
    <div class="cont_right2 round">
      <div class="board_wrap">
        <div class="cont_title">{{ $t("menu.serviceCenterSubmenu.notice")}}
          <div class="board_serch">
            <div class="search_text">
              <input type="search" v-bind:placeholder='$t("description.inputKeyword")' v-model="schWord"></div>
            <a class="serch_btn" @click="getNoticeList(1)">{{ $t("button.search")}}</a>
          </div>
        </div>
        <div class="table">
          <table class="board">
            <colgroup>

              <col width="*">
              <col width="180">
              <col width="150">
            </colgroup>
            <thead>
            <tr>
              <th>{{$t("word.tit")}}</th>
              <th>{{$t("word.rgDt")}}</th>
              <th>{{$t("word.clickCnt")}}</th>
            </tr>
            </thead>
            <tbody>

            <!--중요 공지사항-->
            <template v-for="item01 in yList">
              <template v-for="item02 in item01.descList">
                <tr class="b_notice">
                  <td class="align_l" colspan="3">
                    <div class="td_notice">{{$t("word.notice")}}</div>
                    <a href="javascript:void(0)" @click="searchCtnt(item02.notcSeq)">[{{item01.notcSectnNm}}]
                      {{item02.notcTit}}</a>
                  </td>
                </tr>
              </template>
            </template>

            <!--일반 공지사항-->
            <template v-for="item03 in nList">
              <tr>
                <td class="align_l"><a href="javascript:void(0)" @click="searchCtnt(item03.notcSeq)">[{{item03.notcSectnNm}}]
                  {{item03.notcTit}}</a></td>
                <td>{{item03.rgDt}}</td>
                <td class="align_r">{{item03.srchCnt}}</td>
              </tr>
            </template>


            </tbody>
          </table>
        </div>

        <!--페이징 처리-->
        <span class="paging">
            <a href="javascript:void(0)" v-if="currentPage>5" class="" @click="getNoticeList(1)">
            <img src="@/assets/image/common/p_first.png" title="처음">
            </a>
            <a href="javascript:void(0)" v-if="currentPage>1" class=""  @click="getNoticeList(currentPage-1)">
             <img src="@/assets/image/common/p_before.png" title="이전">
            </a>
          <template v-for="i in pageList">
            <li>
              <a :class="i == currentPage ? 'paging_on':''" @click="getNoticeList(i), page = i" v-bind:id="i" >{{ i }}</a>
            </li>
          </template>
            <a href="javascript:void(0)" class="" @click="getNoticeList(currentPage+1) ">
             <img src="@/assets/image/common/p_last.png" title="다음">
            </a>
            <a href="javascript:void(0)" class="" @click="getNoticeList(pageCnt)">
              <img src="@/assets/image/common/p_end.png" title="끝">
            </a>

        </span>
      </div>

    </div>
    <div style='clear:both;'></div>
  </div>
</template>
<script>
  let pageNum = 1;
  export default {
    name: "noticeList",
    data() {
      return {
        noticeList: [], yList: [], nList:[], pageCnt: 1, currentPage: 1,pageList : [],
        schWord: ''
      }
    },
    created() {
      this.$http.get('/api/v1/notice/getYList').then((response)=>{
        this.yList = response.data.dataList;
      });
      this.getNoticeList(pageNum);


    },
    methods: {

      getNoticeList: function (page) {  //페이지 선택시 공지사항 조회
        pageNum = page;
        this.currentPage = page;
        /*검색*/
        let schWord = '';
        if (this.schWord) {
          schWord = this.schWord;
        }
        this.$http.get('/api/v1/notice/getNoticeMaxPage?schWord=' + schWord).then((res) => {
          this.pageCnt = res.data.dataList[0].maxPage;
        })

        this.$http.get('/api/v1/notice/getNList?schWord=' + schWord + '&page=' + page).then((res) => {
          this.nList = res.data.dataList;
          this.paging(page);
        })

      },

      //noticeView로 이벤트를 보내는 컴포넌트
      searchCtnt(notcSeq) {
        this.$http.get('/api/v1/notice/updateSrchCnt?notcSeq=' + notcSeq);
        this.$router.push({name: 'noticeView'});
        this.$store.commit('SEARCH_NOTCSEQ', {notcSeq: notcSeq});
      } ,
      paging: function (currPage) {
        let firstIndex = 1;
        let lastIndex = this.pageCnt < 5 ? this.pageCnt : 5;

        if(this.pageCnt > 5){
          firstIndex = 5 * (Math.ceil(currPage/5)) - 4;
          lastIndex = (firstIndex + 4) > this.pageCnt ? this.pageCnt : firstIndex + 4;
        }
        this.pageList = [];
        for(let i =firstIndex; i <=lastIndex; i++){
          this.pageList.push(i);
        }
      }
    }
  }
</script>

<style scoped>

</style>
