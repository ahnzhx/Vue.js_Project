<template>
  <div>
    <div class="bottom_list round">
      <span class="tabs tab3">
              <ul>
                  <li>
                      <a :class="[{ on : 0 == activeIndex }]" @click="activeIndex = 0">{{ $t("word.conc") }}</a>
                  </li>
                  <li>
                      <a :class="[{ on : 1 == activeIndex }]" @click="activeIndex = 1">{{ $t("word.day") }}</a>
                  </li>
                  <li>
                      <a :class="[{ on : 2 == activeIndex }]" @click="activeIndex = 2">{{ $t("word.coinInfo") }}</a>
                  </li>
              </ul>
      </span>

      <DIV id="tab1" v-show="activeIndex == 0">
        <div style="display: block">
          <table class="guide_line_d">
            <colgroup>
              <col width="100">
              <col width="280">
              <col width="280">
              <col width="*">
            </colgroup>
            <thead>
            <tr>
              <th>{{ $t('word.conc')+$t('word.time') }}</th>
              <th>{{ $t('word.conc')+$t('word.price') }} <label>({{ $store.state.buyCoinUnit }})</label></th>
              <th>{{ $t('word.conc')+$t('word.Quantity') }} <label>({{ $store.state.sellCoinUnit }})</label></th>
              <th>{{ $t('word.conc')+$t('word.sum') }} <label>({{ $store.state.buyCoinUnit }})</label></th>
            </tr>
            </thead>
          </table>

          <div style="width: 100%; height: 240px; overflow-y:scroll; position: relative;">
            <div
              style="width:100%; height:225px; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow-x:hidden; position: absolute; box-sizing: border-box;">
              <table class="conclusion_history_box">
                <colgroup>
                  <col width="100">
                  <col width="280">
                  <col width="280">
                  <col width="*">
                </colgroup>
                <tbody>
                <template v-for="conc in concInfo">
                  <tr>
                    <td>{{conc.concDt}}<em>{{conc.tmstmp}}</em></td>
                    <td class="coin_buy">{{formatPrice(conc.mktprPr)}}</td>
                    <td>{{(conc.concAmnt*1).toFixed(8)}}</td>
                    <td>{{(conc.totConcPr*1).toFixed(8)}}</td>
                  </tr>
                </template>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </DIV>

      <DIV id="tab2" v-show="activeIndex == 1">
        <div>
          <table class="guide_line guide_line_b">
            <colgroup>
              <col width="100">
              <col width="280">
              <col width="280">
              <col width="*">
            </colgroup>
            <thead>
            <tr>
              <th>{{ $t('word.date') }}</th>
              <th>{{ $t('word.closingPrice') }} <label>({{ $store.state.buyCoinUnit }})</label></th>
              <th>{{ $t('word.dayBefore') }}</th>
              <th>{{ $t('word.trnsQty')}} <label>({{$store.state.sellCoinUnit}})</label></th>
            </tr>
            </thead>
          </table>
        </div>
        <article style="min-width:990px;">
          <div style="overflow: hidden;height: 360px;">
            <div style="position: relative; overflow: hidden; width: 100%; height: 360px;">
              <div
                style="width:100%;position: absolute; top: 0px; left: 0px; right: 0px; bottom: 0px; overflow-y: scroll; margin-right: 0px; margin-bottom: 0px;">
                <table class="trade_bot_list">
                  <colgroup>
                    <col width="100">
                    <col width="280">
                    <col width="280">
                    <col width="*">
                  </colgroup>
                  <tbody>
                  <template v-for="concDay in concDayInfo">
                    <tr class="up">
                      <td class="center"><p>{{concDay.concDay}}</p></td>
                      <td><strong class="down">{{concDay.concUntpr}}</strong></td>
                      <td :class="concDay.prevDayOfConcPr == -1 ? 'up' : 'down'">
                        {{(concDay.prevDayOfConcPr * 1).toFixed(2)}}%
                      </td>
                      <td class="rAlign">{{(concDay.totConcPr)}}</td>
                    </tr>
                  </template>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </article>
      </DIV>
      <DIV id=tab3 v-show="activeIndex == 2">
        <article style="min-width:990px;">
          <div style="overflow: hidden;height: 392px;">

            <div style="position: relative; overflow: hidden; width: 100%; height: 392px;">
              <div
                style="width:100%;position: absolute; top: 0px; left: 0px; right: 0px; bottom: 0px; overflow-y: scroll; margin-right: 0px; margin-bottom: 0px; padding: 40px 60px; box-sizing: border-box;">
                <div>
                  <img :src="getPic(mrktId)" style="width: 40px; vertical-align: bottom;">
                  <span class="coin_info_title">{{coinInfo.coinNm}}</span>

                  <div style="float: right; line-height: 50px;">
                      <a v-if="coinInfo.rprsHpgeAddr" :href="coinInfo.rprsHpgeAddr" class="coin_info_btn">웹사이트</a>
                      <a v-else></a>

                      <a v-if="coinInfo.wpprDwnldAddr" :href="coinInfo.wpprDwnldAddr" class="coin_info_btn">백서</a>
                      <a v-else></a>
                  </div>
                </div>
                <div>
                  <table class="coin_info_table mt20">
                    <colgroup>
                      <col width="12%">
                      <col width="21.33%">
                      <col width="12%">
                      <col width="21.33%">
                      <col width="12.33%">
                      <col width="21.33%">
                    </colgroup>
                    <tbody>
                    <tr>
                      <th>법인재단명</th>
                      <td>{{coinInfo.crprFndtNm}}</td>
                      <th>최초발행</th>
                      <td>{{coinInfo.frstIssDt}}</td>
                      <th>설립일자</th>
                      <td>{{coinInfo.estbDt}}</td>
                    </tr>
                    <tr>
                      <th>법인소재지</th>
                      <td>{{coinInfo.crprLctnNm}}</td>
                      <th>시가총액</th>
                      <td>{{coinInfo.mktprAllPr}}</td>
                      <th>합의 프로토콜</th>
                      <td>{{coinInfo.agrPrtclVal}}</td>
                    </tr>
                    <tr>
                      <th>총 발행한도</th>
                      <td>{{coinInfo.allIssLmitVaL}}</td>
                      <th>상장거래소</th>
                      <td>{{coinInfo.lstdXchgAmnt}}</td>
                      <th rowspan="2">채굴 보상량</th>
                      <td rowspan="2">{{coinInfo.mnngCmpnsAmnt}}</td>
                    </tr>
                    <tr>
                      <th>대표인물</th>
                      <td>{{coinInfo.rprsPrsnNm}}</td>
                      <th>블록 생성주기</th>
                      <td>{{coinInfo.blckCrtnCcleVal}}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
                <div class="coin_info_txt mt40">

                  <div v-if="coinInfo.coinIntroCtnt">
                    <strong>코인소개</strong>
                    <p>
                      <span>{{coinInfo.coinIntroCtnt}}</span>
                    </p>
                  </div>
                  <div class="mt40" v-if="coinInfo.coinFeatCtnt">
                    <strong>코인특징</strong>
                    <p style="word-break: keep-all;">
                      <span>{{coinInfo.coinFeatCtnt}}</span>
                    </p>
                  </div>
                  <div class="mt40" v-if="coinInfo.coreValCtnt">
                    <strong>코인핵심</strong>
                    <p style="word-break: keep-all;">
                      <span>{{coinInfo.coreValCtnt}}</span>
                    </p>
                  </div>
                  <div class="mt40" v-if="coinInfo.utlzFldCtnt">
                    <strong>코인활용분야</strong>
                    <p style="word-break: keep-all;">
                      <span>{{coinInfo.utlzFldCtnt}}</span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </article>
      </DIV>
    </div>
  </div>
</template>
<script>

  export default {
    //체결리스트 조회
    name: "conclusion",
    created() {
      //this.$EventBus.$on('mrktInfo', this.mrktNmres);
      this.$EventBus.$on('toConclusion', this.mrktNmres);
    },
    data() {
      return {
        activeIndex: 0,
        concInfo: [],
        concDayInfo: [],
        coinInfo: [],
        mrktNm: '비트코인',
        mrktId: 'BTC'
      }
    },
    methods: {
      //소수점 8자리
      formatPrice(value) {
        let string = "" + value;  // 문자로 바꾸기.
        string = string.replace(/[^-+\.\d]/g, "");  // ±기호와 소수점, 숫자들만 남기고 전부 지우기.
        let regExp = /^([-+]?\d+)(\d{3})(\.\d+)?/;  // 필요한 정규식.
        while (regExp.test(string)) string = string.replace(regExp, "$1" + "," + "$2" + "$3");  // 쉼표 삽입.
        return (string / 1).toFixed(8);
      },
      //체결금액 자리수
      concFormatPrice(value) {
        let string = "" + value;  // 문자로 바꾸기.
        string = string.replace(/[^-+\.\d]/g, "");  // ±기호와 소수점, 숫자들만 남기고 전부 지우기.
        let regExp = /^([-+]?\d+)(\d{3})(\.\d+)?/;  // 필요한 정규식.
        while (regExp.test(string)) string = string.replace(regExp, "$1" + "," + "$2" + "$3");  // 쉼표 삽입.
        return (string / 1).toFixed(1);
      },
      //코인이미지 불러오기
      getPic(img) {
        return require(`@/assets/image/coin/${img}_b.png`);
      },
      //코인정보 조회
      mrktNmres(coin) {


        let id = (coin.market == undefined ? coin.code : coin.market);
        id = id + "";

        let baseCoin = (id).split("-")[0];
        this.mrktId = (id).split("-")[1];

        this.mrktNm = (!coin.mrktNm ? '' : coin.mrktNm);
        this.$http.post('/api/v1/asstsHld/hldList', {mmbrNmbr: this.mmbrNmbr,coinId:this.priceUnit}).then((res) => { //코인클릭시 보유자산 조회(주문가능금액 조회하기위해)
          this.buyAsset = res.data.dataList[0]!=null?res.data.dataList[0].hldgAmnt:0;
        });

        this.$http.get('/api/v1/coin/getCoinInfo/' + this.mrktId)
          .then((response) => {
            console.dir(response.data);
            this.coinInfo = response.data;
          });

        //체결리스트 조회
        this.$http.get('/api/v1/trade/getConcDayList/' + id)
          .then((res) => {
            this.concInfo = res.data.dataList;
          });

        //일별 조회
        this.$http.get('/api/v1/trade/getConcList/' +  id)
          .then((response) => {
            this.concDayInfo = response.data.dataList;
          });


      }
    }
  }
</script>

<style scoped>

</style>
