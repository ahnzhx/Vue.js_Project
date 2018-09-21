<template>
  <article class="main3 main4">
  <div class="cont_right2 round">
    <div class="board_wrap">
      <div class="cont_title no_border">{{ $t('menu.serviceCenterSubmenu.paperInfo') }}</div>
      <span class="tabs tab8 border-right">
        <ul>
          <li>
            <a id=tab1m1 :class="[{ on : 0 == activeIndex }]" href="#" @click="activeIndex = 0; return false;">{{ $t('word.proofOfDpst') }}<span>({{ $t('word.myAcc') }})</span></a>
          </li>
          <li>
            <a id=tab1m2 :class="[{ on : 1 == activeIndex }]" href="#" @click="activeIndex = 1; return false;">{{ $t('word.proofOfDpst') }}<span>({{ $t('word.otherAcc') }})</span></a>
          </li>
          <li>
            <a id=tab1m3 :class="[{ on : 2 == activeIndex }]" href="#" @click="activeIndex = 2; return false;">{{ $t('word.accNmbr') }}<br>{{ $t('word.reset') }}</a>
          </li>
          <li>
            <a id=tab1m4 :class="[{ on : 3 == activeIndex }]" href="#" @click="activeIndex = 3; return false;">{{ $t('word.cphNmbr') }}<br>{{ $t('word.reset') }}</a>
          </li>
          <li class="border_bottom">
            <a id=tab1m5 :class="[{ on : 4 == activeIndex }]" href="#" @click="activeIndex = 4; return false;">{{ $t('word.reqToRelsWthwlLimit') }}</a>
          </li>
          <li class="border_bottom">
            <a id=tab1m6 :class="[{ on : 5 == activeIndex }]" href="#" @click="activeIndex = 5; return false;">{{ $t('word.withdrawal') }}</a>
          </li>
          <li class="border_bottom">
            <a id=tab1m7 :class="[{ on : 6 == activeIndex }]" href="#" @click="activeIndex = 6; return false;">OTP {{ $t('word.reset') }}</a>
          </li>
          <li class="border_bottom">
            <a id=tab1m8 :class="[{ on : 7 == activeIndex }]" href="#" @click="activeIndex = 7; return false;">{{ $t('word.unlockAcc') }}</a>
          </li>
        </ul>
      </span>

      <!--1.입금사실 증명 (본인)-->
      <DIV id=tab1c1 v-show="activeIndex == 0">
        <div class="cs no_mt no_border">
          <h4>{{ $t('word.proofOfDpst') }}({{ $t('word.myAcc') }})</h4>
          <ul>
            <li>본인명의 계좌에서 원화(KRW)를 입금하였으나 비트플러스 전용번호에 입금 처리되지 않은 경우</li>
            <li>가입시 등록하지 않은 본인명의 다른 계좌에서 입금했을 경우</li>
            <li>입금자명을 입력하지 않았거나 또는 숫자코드를 입력하지 않은 경우</li>
            <li>입급자명을 다르게 입력한 경우</li>
            <li>요청급액과 실제 입금액이 다르거나 또는 나누어서 입금했을 경우</li>
            <li>타인명의 계좌에서 입금했다면 <span class="blue">'입금 사실 증명(타인계좌)'</span>을 참고해주세요.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>입금 확인 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red">({{ $t("word.necessary")}})</span>
              <br>3. 거래내역 사본<span class="red">({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>

        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
                <div v-if="!idImgData" class="img_box"></div>
                <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
                </div>
                <input id="idImg01" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg01')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg") }}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg01" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg01')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>

              </ul>
            </div>
          </div>

          <div style="margin-top: 20px;">
            <div class="certify_box">
              <strong>거래내역 사본<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!etcImgData" class="img_box" ></div>
              <div v-else class="img_box">
               <img :src="etcImgData" style="width:140px; height:180px;">
              </div>
              <input id="etcImg01" type="file" accept="image/*" @change="etcImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('etcImg01')">파일선택</button>
            </div>

            <div class="certify_box">
              <ul style="margin: 0;">
                <li style="color: #2b2b2b;"> '받는 사람, 금액, 거래일자'가 표시된 거래내역 사본을 보내주시기 바랍니다.</li>
              </ul>
              <ul>
                <li> &#10003; 통장사본 또는 인터넷뱅킹 거래내역 화면을 캡쳐 또는 촬영 후 보내주셔도 됩니다.</li>
              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--2.입금사실증명(타인계좌)-->
      <DIV id=tab1c2 v-show="activeIndex == 1">
        <div class="cs no_mt no_border">
          <h4>입금 사실 증명(타인 계좌에서 입금)</h4>
          <ul>
            <li>타인의 계좌에서 입금했을 때 모두 해당.</li>
            <li>타인의 계좌에서 입금하고 본인 입금자면으로 입력했을 경우</li>
            <li>타인의 계좌에서 입금하고 요청금액과 실제 입금액이 다른 경우 또는 나누어서 입금한 경우</li>
            <li>타인명의 계좌에서 원화(KRW)를 입금하였으나 비트플러스 전용번호에 입금 처리되지 않은 경우, 아래 요청 방법대로 메일을 보내주세요.</li>
            <li>본인명의 계좌에서 입금했다면 <span class="blue">'입금 사실 증명(본인계좌)'</span>을 참고해주세요.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>입금 확인 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>

            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red">({{ $t("word.necessary")}})</span>
              <br>3. 송금확인증 사본<span class="red">({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>


        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg02" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg02')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg02" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg02')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li> &#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red"> &#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li> &#10003;주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red"> &#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>
              </ul>
            </div>
          </div>

          <div style="margin-top: 20px;">
            <div class="certify_box">
              <strong>송금확인증<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!etcImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="etcImgData" style="width:140px; height:180px;">
              </div>
              <input id="etcImg02" type="file" accept="image/*" @change="etcImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('etcImg02')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul style="margin: 0;">
                <li style="color: #2b2b2b;"> 발급받으신 송금확인증을 스캔 또는 촬영해서 첨부 바랍니다.</li>
              </ul>
              <ul>
                <li> &#10003; 송금확인증은 송금 '확인번호'와 '일련번호'가 표시되어 있는 서류로 해당 송금 내역의 유효성 검증이 가능한 문서입니다.</li>
                <li> &#10003; 일반적으로 거래은행 인터넷 뱅킹의 별도 메뉴에서 출력 가능하며 지점에 직접 방문하여 발급받을 수 있습니다. (거래은행 고객센터에 문의)</li>
              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--3.계좌번호 초기화-->
      <DIV id=tab1c3 v-show="activeIndex == 2">
        <div class="cs no_mt no_border">
          <h4>계좌번호 초기화</h4>
          <ul>
            <li>계좌번호를 변경하면 초기화 후 직접 다시 계좌인증을 진행해야 합니다.</li>
            <li>계좌번호 초기화 후 재 등록 전까지 KRW 출금이 불가능 합니다.</li>
            <li>위 조치는 피싱이나 금융사고 예방을 위한 조치입니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>계좌번호 초기화 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red">({{ $t("word.necessary")}})</span>
              <br>3. 기존 등록된 계좌의 통장사본<span class="red">({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>


        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg03" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg03')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg03" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg03')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>
              </ul>
            </div>
          </div>

          <div style="margin-top: 20px;">
            <div class="certify_box">
              <strong>현재 등록된 계좌의 통장사본(표지)<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!etcImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="etcImgData" style="width:140px; height:180px;">
              </div>
              <input id="etcImg03" type="file" accept="image/*" @change="etcImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('etcImg03')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul style="margin: 0;">
                <li style="color: #2b2b2b;"> '계좌번호' 및 '예금주가 적힌 통장사본을 스캔 또는 촬영해서 첨부 바랍니다.</li>
              </ul>
              <ul>
                <li> &#10003; 인터넷뱅킹의 경우 [통장표지출력] 서비스 화면을 캡쳐할 수 있습니다.</li>
              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--4.휴대폰 번호 초기화-->
      <DIV id=tab1c4 v-show="activeIndex == 3">
        <div class="cs no_mt no_border">
          <h4>휴대폰 번호 초기화</h4>
          <ul>
            <li>휴대폰 번호를 변경하려면 초기화 후 직접 다시 인증해야 합니다.</li>
            <li>휴대폰 번호 초기화 후 <span class="blue">재등록 전까지 거래 및 KRW 입/출금이 불가능</span> 합니다.</li>
            <li>위 조치는 휴대폰 도난, 피싱이나 금융사고 예방을 위한 조치 입니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>휴대폰 번호 초기화 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red">({{ $t("word.necessary")}})</span>
              <br>3. 기존 등록된 계좌의 통장사본<span class="red">({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>



        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg04" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg04')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg04" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg04')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>

              </ul>
            </div>
          </div>

          <div style="margin-top: 20px;">
            <div class="certify_box">
              <strong>각 통신사 가입증명서<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!etcImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="etcImgData" style="width:140px; height:180px;">
              </div>
              <input id="etcImg04" type="file" accept="image/*" @change="etcImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('etcImg04')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul style="margin: 0;">
                <li style="color: #2b2b2b;"> 통신사별로 가입증서를 발급받아 사진 촬영 후 보내주시기 바랍니다.</li>
              </ul>
              <table>
                <tr>
                  <th>SK</th>
                  <th>KT</th>
                  <th>LG</th>
                </tr>
                <tr>
                  <td>이용계약증명서</td>
                  <td>가입원부증명서</td>
                  <td>가입사실증명서</td>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </DIV>
      <!--5. 출금 제한 해제 요청-->
      <DIV id=tab1c5 v-show="activeIndex == 4">
        <div class="cs no_mt no_border">
          <h4>출금 제한 해제 요청</h4>
          <ul>
            <li>해킹 의심 또는 이상거래 등이 발견되면 관리자 판단이나 회원님 요청에 의해 계정의 안전한 보호를 위하여 출금이 제한됩니다.</li>
            <li>출금제한 해제 시 본인 확인이 필요한 경우 본인인증 서류 제출이 필요합니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>출금 제한 해제 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red">({{ $t("word.necessary")}})</span>
              <br>3. 거래 내역 사본<span class="red">({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>



        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg05" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg05')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg05" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg05')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>

              </ul>
            </div>
          </div>

          <div style="margin-top: 20px;">
            <div class="certify_box">
              <strong>각 통신사 가입증명서<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!etcImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="etcImgData" style="width:140px; height:180px;">
              </div>
              <input id="etcImg05" type="file" accept="image/*" @change="etcImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('etcImg05')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul style="margin: 0;">
                <li style="color: #2b2b2b;"> '받는 사람, 금액, 거래일자'가 표시된 거래내역 사본을 첨부 바랍니다.</li>
              </ul>
              <ul>
                <li> &#10003; 통장사본 또는 인터넷뱅킹 거래내역 화면을 캡쳐 또는 촬영 후 보내주셔도 됩니다.</li>
              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--6. 회원탈퇴-->
      <DIV id=tab1c6 v-show="activeIndex == 5">
        <div class="cs no_mt no_border">
          <h4>회원탈퇴</h4>
          <ul>
            <li>잔액이 남아있는 경우 회원탈퇴가 불가능 하니 사용중인 KRW 및 암호화폐의 잔액을 모두 출금해 주시기 바랍니다.</li>
            <li>탈퇴 시 비트플러스에 등록된 회원님의 정보는 모두 삭제되며 복구되지 않습니다.<br>
              단, 관계법령에 따라서 회사가 보관해야 하는 정보는 일정 기간 보관됩니다.</li>
            <li>입출금 내역이 없는 이메일 인증 회원의 경우에는 별도의 서류제출 없이 탈퇴가 가능합니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>회원 탈퇴 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. {{ $t("word.holdingIdImg")}}<span class="red"> ({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>



        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg06" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg06')">파일선택</button>
            </div>
            <div class="certify_box">

              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg06" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg06')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>

              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--7. otp초기화-->
      <DIV id=tab1c7 v-show="activeIndex == 6">
        <div class="cs no_mt no_border">
          <h4>OTP 초기화</h4>
          <ul>
            <li>휴대폰 교체/분실 또는 인증 앱을 지워서 OTP 인증을 할 수 없는 경우 본인인증 서류 제출이 필요합니다.</li>
            <li>인증서 제출 후 관리자가 승인하면 OTP 인증이 초기화 됩니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>OTP 초기화 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">({{ $t("word.necessary")}})</span>
              <br>2. 신분증을 들고 있는 본인 사진<span class="red"> ({{ $t("word.necessary")}})</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>

        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">1:1 문의하기</a> 를 이용해주세요.</p>
        </div>

        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg07" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg07')">파일선택</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>

          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
              <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg07" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg07')">{{ $t("button.selectFile")}}</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>

              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <!--8. 계정 잠금 해제-->
      <DIV id=tab1c8 v-show="activeIndex == 7">
        <div class="cs no_mt no_border">
          <h4>{{$t("word.unlockAcc")}}</h4>
          <ul>
            <li>게정 잠금을 해제하기 위해서 본인인증 서류 제출이 필요합니다.</li>
            <li>인증서 제출 후 관리자가 승인하면 계정 잠금이 해제됩니다.</li>
          </ul>
        </div>
        <div class="cs no_border">
          <h4>계정 잠금 해제 요청 방법</h4>
          <p>아래 필요한 첨부파일을 준비하신 후 하단의 각 항목에 파일을 업로드해주시길 바랍니다.<br>
            파일 업로드가 모두 끝난뒤 최하단의 신청하기 버튼을 눌러주시면 접수됩니다.</p>
          <ul>
            <li class="fl w25">첨부파일</li>
            <li class="fl nodot">1. 신분증 사진 <span class="red">(필수)</span><br>2. 신분증을 들고 있는 본인 사진<span class="red"> (필수)</span>
              <br><small>( 파일당 10MB 이하의 JPG/JPEG/PNG/GIF 파일만 가능 )</small></li>
          </ul>
        </div>
        <div class="sign">
          <p>&#8270; 보내주신 자료 검토 후 추가적인 확인이 필요할 경우 담당자가 연락을 드립니다. 서류 접수 후 1~3일 이상 소요될 수 있습니다.</p>
          <p>&#8270; 인증 자료 제출 외 문의는 <a href="#">{{$t("word.necessary")}}</a> 를 이용해주세요.</p>
        </div>
        <div class="certify_con">
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.id")}}<span>&nbsp;{{$t("menu.serviceCenterSubmenu.contactUs")}}</span></strong>
              <div v-if="!idImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="idImgData" style="width:140px; height:180px;">
              </div>
              <input id="idImg08" type="file" accept="image/*" @change="idImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('idImg08')">{{ $t("button.selectFile")}}</button>
            </div>
            <div class="certify_box">
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li>&#10003; 얼굴과 신분증 모두 잘 보이도록 사진을 찍어주세요.</li>
                <li>&#10003; 사진은 비대면 실명확인 용도로만 사용되며, 신분증 글자를 읽을 수 있어야 합니다.</li>
              </ul>
            </div>
          </div>
          <div>
            <div class="certify_box">
              <strong>{{ $t("word.holdingIdImg")}}<span>&nbsp;{{ $t("word.necessary")}}</span></strong>
              <div v-if="!holdingIdImgData" class="img_box" ></div>
              <div v-else class="img_box">
                <img :src="holdingIdImgData" style="width:140px; height:180px;">
              </div>
              <input id="holdingIdImg08" type="file" accept="image/*" @change="holdingIdImgFile" style="display: none">
              <button class="file_btn2" @click="leadToInput('holdingIdImg08')">{{ $t("button.selectFile")}}</button>
            </div>
            <div class="certify_box">
              <ul>
                <li style="color: #2b2b2b;">신분증을 들고 있는 본인의 사진을 업로드 해 주시기 바랍니다.</li>
              </ul>
              <ul>
                <li>&#10003; 신분증에 비트플러스 인증요청, 목적, 이메일 주소, 촬영일자를 자필로 써서 붙여주세요.</li>
                <li class="red">&#10003; 주민번호 뒤 7자리는 가려주시기 바랍니다. (개인정보 보호)</li>
                <li>&#10003; 주민등록증 대신 운전면허증이나 여권도 가능하며, 마찬가지로 운전면허 번호, 여권번호는 가려주시기 바랍니다.
                  (주민등록증, 운전면허증, 여권만 신분증으로 인정)</li>
                <li class="red">&#10003; 촬영시간을 기준으로 제출일 이전에 찍은 사진이나 메모가 없는 사진은 인정하지 않습니다.</li>
              </ul>
            </div>
          </div>
        </div>
      </DIV>
      <a href="#" class="identity_btn" @click="fileInsert">{{ $t("word.apply")}}</a>
    </div>
  </div>
  <div style='clear:both;'></div>
  </article>
</template>

<script>
    export default {
        name: "paperInfo",
      data() {
        return {
          activeIndex: 0,
          idImg: null,
          holdingIdImg:null,
          etcImg:null,
          mmbrNmbr:localStorage.getItem("mmbrNmbr"),
          idImgData:"", //신분증
          holdingIdImgData:"", //신분증 들고있는 사진
          etcImgData:""  //기타 사진

        }
      },
      methods:{
        idImgFile(event){
          this.idImg = event.target.files[0];
          var reader = new FileReader();
          reader.onload = (e) =>{
            this.idImgData =e.target.result;
          }
          reader.readAsDataURL(this.idImg);
        },
        holdingIdImgFile(event){
          this.holdingIdImg = event.target.files[0];
          var reader = new FileReader();
          reader.onload = (e) =>{
            this.holdingIdImgData =e.target.result;
          }
          reader.readAsDataURL(this.holdingIdImg);

        },
        etcImgFile(event){
          this.etcImg = event.target.files[0];
          var reader = new FileReader();
          reader.onload = (e) =>{
            this.etcImgData = e.target.result;
          }
          reader.readAsDataURL(this.etcImg);
        },
        fileInsert(){

          let cmFormData = new FormData(); //공통이미지
          cmFormData.append('idImg', this.idImg);
          cmFormData.append('holdingIdImg', this.holdingIdImg);
          cmFormData.append('mmbrNmbr', this.mmbrNmbr);
          cmFormData.append('activeIndex', this.activeIndex+1);

          if(!this.idImg||!this.holdingIdImg) {
            alert("필수 사진을 모두 등록해주세요.");
          }

          this.$http.post("/api/file/paperInfo/cmFileUpload", cmFormData,
            {headers:{'Content-Type': 'multipart/form-data'}}).then((res)=>{
            alert("파일이 등록되었습니다.");
            this.idImgData = "";
            this.holdingIdImgData = "";
          });

          if(this.etcImg){
            let etcFormData = new FormData(); //기타이미지
            etcFormData.append('etcImg', this.etcImg);
            etcFormData.append('mmbrNmbr', this.mmbrNmbr);
            etcFormData.append('activeIndex', this.activeIndex+1);

            this.$http.post("/api/file/paperInfo/etcFileUpload", etcFormData,
              {headers:{'Content-Type': 'multipart/form-data'}}).then((res)=>{
              this.etcImgData = "";
            });
          }
        },
        leadToInput(id) {
          $('#' + id).trigger('click');
        }
      }
    }
</script>

<style scoped>

</style>
