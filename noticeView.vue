<template>
  <div class="cont_right2 round">
<!--  <div style="background-color:white; margin:10px;">
    <div style=" padding:50px;">-->
      <h3>{{ $t("menu.serviceCenterSubmenu.notice") }}</h3>
      <div class="viewB">
        <div class="titB">
          <strong class="tit">
              {{notcTit}}
          </strong>
          <span>
            <em>{{ $t("word.rgDt") }}<i> {{rgDt}}|</i>
              <em>{{ $t("word.clickCnt") }} {{srchCnt}}</em>
            </em>
          </span>
        </div>
        <div class="txtB">
          <div id="markdown_notice_body">
            {{notcCtnt}}
          </div>
        </div>
      </div>
      <div class="board_btn align_r">
        <a class="board_btn_list" href="javascript:history.back();">{{ $t("button.list") }}</a>
      </div>
    </div>

</template>

<script>
  export default {
    name: "noticeView" ,

    data() {
      return {
        notcSeq:'',
        notcTit:'',
        notcCtnt:'',
        rgDt:'',
        srchCnt:'', page: ''
        /*  notcSeq: this.$route.params.notc_seq*/  //파라미터로 받은 공지사항 시퀀스 저장
      }
    } ,
    created() {

      //이벤트를 받는 컴포넌트
      this.notcSeq = localStorage.getItem("notcSeq");

      this.$http.get('/api/v1/notice/getNoticeView?notcSeq=' + this.notcSeq)
        .then((response) => { //=> 는 function() 와같은 표현이다
          this.notcTit = response.data.notcTit;
          this.notcCtnt = response.data.notcCtnt;
          this.rgDt = response.data.rgDt;
          this.srchCnt = response.data.srchCnt;
          this.page = response.data.page;
        });
    },
    methods: {
      onClick: function (page) {
        this.$router.push({name: 'noticeList', params:{page: page}}); //목록으로 이동
        alert("noticeView의 page  "+page);
      }
    }
  }
</script>

<style scoped>
  .titB {
    padding: 14px 19px;
  }

  .txtB {
    width: 100%;
    padding: 15px 0 30px;
    border-top: 1px solid #f1f1f4;
  }

  #markdown_notice_body {
    margin: 0;
    padding: 0;
    border: 0;
    word-break: break-all;
    width: 100%;
    padding: 0;
  }

  .viewB .titB strong.tit {
    display: block;
    color: #2b2b2b;
    font-size: 20px;
    font-weight: 500;
  }

  .viewB .titB span em {
    color: #999;
    font-size: 12px;
    font-family: Dotum, Noto Sans KR;
  }

  #markdown_notice_body p {
    padding: 0;
    margin: 1em 0;
    line-height: 1.5em;
  }

  .viewB {
    width: 100%;
    margin: 20px 0 0;
    border-top: 2px solid #9b9fa8;
  }

  h3 {
    height: 40px;
    color: #2b2b2b;
    font-size: 27px;
    line-height: 40px;
  }
</style>


