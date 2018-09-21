<template>
  <div>
    <article class="main main3">
      <section class="login">
        <div class="cont_login round">
          <h3 class="title">{{ $t('word.signup') }}</h3>
          <form>
            <!-- email -->
            <div class="login_inp">
              <input name="email"
                     class="inp_login"
                     id="email"
                     type="text"
                     :placeholder="$t('word.email')"
                     v-model="form.email"
                     v-validate="'required|email|numEng'"
                     :data-vv-as="$t('word.email')">
            </div>
            <div class="error_msg" v-show="errors.has('email')">{{ errors.first("email") }}</div>

            <!-- password -->
            <div class="login_inp">
              <input name="password"
                     class="inp_login"
                     id="password"
                     type="password"
                     ref="confirmation"
                     :placeholder="$t('word.password')"
                     v-model="form.password"
                     v-validate="'required|password'"
                     :data-vv-as="$t('word.password')">
            </div>
            <div class="error_msg" v-show="errors.has('password')">{{ errors.first("password") }}</div>
            <div class="login_info">
              <p>{{ $t("description.passwordRule") }}</p>
            </div>
            <!-- chkpassword -->
            <div class="login_inp">
              <input name="chkpassword"
                     class="inp_login"
                     id="chkpassword"
                     type="password"
                     :placeholder="$t('word.chkpassword')"
                     v-model="form.chkpassword"
                     v-validate="'required|confirmed:confirmation'">

            </div>
            <div class="error_msg" v-show="errors.has('chkpassword')">{{ $t('validate.notEqualPassword') }}</div>
            <!-- chkTermsOfUse -->
            <div class="join_foot">

              <li><input name="chkTermsOfUse"
                         class="join_chk"
                         type="checkbox"
                         v-model="chkTermsOfUse"
                         v-validate="'required'"
                         :data-vv-as="$t('word.trmsOfUse')">
                <a href="#" @click="show('termsOfUse')" class="join_link">{{ $t("word.trmsOfUse") }}</a>{{ $t("message.agreement") }}

              </li>
              <li><input name="chkPrivatePolicy"
                         class="join_chk"
                         type="checkbox"
                         v-model="chkPrivacyPolicy"
                         v-validate="'required'"
                         :data-vv-as="$t('word.prsnPrvd')">
                <a href="#" @click="show('privacyInfo')" class="join_link">{{ $t("word.prsnPrvd") }}</a>{{ $t("message.agreement") }}
              </li>
              <li><input name="chkAdult"
                         class="join_chk"
                         type="checkbox"
                         v-model="chkAdult"
                         v-validate="'required'"
                         :data-vv-as="$t('word.adult')">{{ $t("message.adultAgreement") }}
              </li>
            </div>
            <div class="error_msg" v-show="errors.has('chkTermsOfUse')">{{ errors.first('chkTermsOfUse')}}</div>
            <div class="error_msg" v-show="errors.has('chkPrivatePolicy')">{{ errors.first('chkPrivatePolicy')}}</div>
            <div class="error_msg" v-show="errors.has('chkAdult')">{{ errors.first('chkAdult')}}</div>

            <div style="margin: 0 auto 8px auto; position: relative; width: 80%;">

              <div class="login_inp" style="float: left; width: 66%; margin-right: 5px;">
                <input name="cphNmbr"
                       class="inp_login"
                       id="cphNmbr"
                       type="text"
                       :placeholder="$t('message.noDashCharByCellphone')"
                       v-model="form.cphNmbr"
                       v-validate="'required|numeric'"
                       :data-vv-as="$t('word.cphNmbr')"
                       :readonly="readonly.cphNmbr"
                       onkeyPress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false">
              </div>

              <button :class=" chkCn ? 'btn_blue':'btn_gray'"
                      id="cphNmbrBtn"
                      type="button"
                      @click="sendCertSmsSend"
                      :disabled="readonly.certSendBtn">

                <template v-if="crtNmbr">
                  <div style="font-size: 15px">
                    {{ $t("button.certNmbrReSend")}}
                  </div>
                </template>

                <template v-else>
                  {{ $t("button.certNmbrSend") }}
                </template>
              </button>

            </div>
            <div class="error_msg" v-show="errors.has('cphNmbr')">{{ errors.first('cphNmbr')}}</div>
            <div class="auth_num_con" style="text-align: center">
              <input name="certNmbr"
                     class="inp_login"
                     id="certNmbr"
                     type="text"
                     :placeholder="$t('word.certNmbr')"
                     v-model="form.certNmbr"
                     v-validate="'required|confirmed:chkCertNmbr'"
                     :data-vv-as="$t('word.certNmbr')">
              <input type="text">
              <div v-show="form.chkCertNmbr" style="line-height: 38px;">{{timer}}</div>
              <input type="hidden" name="chkCertNmbr" ref="chkCertNmbr" v-model="form.chkCertNmbr" v-validate="'required'"/>
            </div>

            <div class="error_msg" v-show="errors.has('chkCertNmbr')">{{ errors.first("certNmbr")}}</div>
            <button id="rgstMmbr" :class=" chkList ? 'btn_join': 'btn_join_fail'" type="button" @click="registMember">
              <span v-show="confirm">{{ $t("button.confirm") }}</span>
              <pulse-loader :loading="loading" :color="color"></pulse-loader>
            </button>
          </form>
        </div>
      </section>
    </article>

    <modal name="privacyInfo"
           :scrollable="true"
           width="70%"
           height="auto">
      <privacy-infomation></privacy-infomation>
    </modal>
    <modal name="termsOfUse" width="70%" height="auto" :scrollable="true">
      <terms-use></terms-use>
    </modal>
    <modal name="mail-send" class="black_bg_op">
      <div class="email_com_box">
        <img src="../../assets/image/sub/send_email_icon.png">
        <p>{{ $t('description.sendCertMail') }}</p>
        <a href="#" class="email_com_box_btn" @click="closed">확인</a>
      </div>
    </modal>
  </div>
</template>

<script>
  import TermsUse from '../home/termsOfUse'
  import PrivacyInfomation from '../home/privacyInformation'
  import PulseLoader from 'vue-spinner/src/PulseLoader'

  const passwordRules = /(?=^.{8,15}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[~!@\#$%<>^&*\()\-=+_\']).*$/;
  const cphNmbrRules = /^01([0|1|6|7|8|9]{1}?)([0-9]{3,4})([0-9]{4})$/;
  const emailRules = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

  export default {
    name: "signup",
    components: {
      PrivacyInfomation,
      TermsUse,
      PulseLoader
    },

    data(){
      return {
        chkTermsOfUse: false,
        chkPrivacyPolicy: false,
        chkAdult:false,
        loading: false,
        color: '#FFFFFF',
        confirm: true,
        intervalid:'',
        crtNmbr:false,
        form:{
          email: '',
          password: '',
          chkpassword: '',
          chkTermUseAgree: this.chkTermsOfUse,
          chkPrivacyAgree: this.chkPrivacyPolicy,
          cphNmbr:'',
          certNmbr:'',
          chkCertNmbr:'',
          chkCphNmbr:''
        },
        readonly:{
          cphNmbr:false,
          certSendBtn:false
        },
        chkEmail:false,
        chkPw:false,
        chkChkPw:false,
        chkCn:false,
        chkCrtn:false,
        min:'3',
        sec:'179',
        remainingSeconds:'00'
      }
    },
    methods:{
      registMember(){
        this.$validator.validateAll().then(result=>{
          if(this.form.chkCertNmbr !== this.form.certNmbr) {
            alert("인증번호가 일치하지 않습니다.");
          }
          if(result){
            this.$modal.show('mail-send');
            if(confirm(this.$t('message.registConfirm'))){
              this.confirm = false;
              this.loading = true;
              this.$http.post('/api/v1/members/regist', this.form).then((res)=>{
                this.$modal.show('mail-send');
              }).catch(()=>{
                alert(this.$t('error.emlAddrDulplicate'));
                this.confirm = true;
                this.loading = false;
              })
            }
          }
        });
      },
      sendCertSmsSend(){
        this.$validator.validate('cphNmbr').then(result => {
          if(result){
            this.$http.get('/authapi/auth/sendCertSMS?cphNmbr='+this.form.cphNmbr).then((res)=>{
              this.form.chkCertNmbr = res.data.key;
              this.form.chkCphNmbr = res.chkCphNmbr;
              this.readonly.cphNmbr = true;
              this.readonly.certSendBtn = true;

              alert(this.$t('message.crtNmbrSend'));
              //타이머
              this.intervalid = setInterval(()=>{
                this.min = Math.round((this.sec -30) / 60);
                this.remainingSeconds = this.sec % 60;

                if (this.remainingSeconds < 10){
                  this.remainingSeconds = "0"+this.remainingSeconds;
                }
                this.sec--;
              }, 1000);


            });
          }
        });
      },
      show(modalNm){
        this.$modal.show(modalNm, {buttons:{title:'Close'}});
      },
      hide(modalNm){
        this.$modal.hide(modalNm);
      },
      closed(e){
        this.$router.push('signin');
      }
    },

    mounted() {
      this.$validator.extend('password', {
        getMessage: field => this.$t('validate.noValidPasswordFormat'),
        validate: value => {
          return passwordRules.test(value)
        }
      }),
        this.$validator.extend('numeric', {
          getMessage: field => this.$t('validate.noValidCphNmbr'),
          validate: value => {
            return cphNmbrRules.test(value)
          }
        }) ,
        this.$validator.extend('numEng', {
          getMessage: field => this.$t('validate.noValidKor'),
          validate: value => {
            return emailRules.test(value)
          }
        })
    },
    watch:{
      'form.email':function(value, oldValue){
        if(this.form.email)
          this.chkEmail= true
      },
      'form.password':function () {
        if(this.form.password)
          this.chkPw = true

      },
      'form.chkpassword':function(){
        if(this.form.chkpassword)
          this.chkChkPw = true

      },
      'form.cphNmbr':function () {
        if(this.form.cphNmbr)
          this.chkCn = true;
      },
      'form.certNmbr':function () {
        if(this.form.certNmbr)
          this.chkCrtn = true;
      },
      timer : function(val){
        if (val == '0:00'){
          clearInterval(this.intervalid);
          this.readonly.certSendBtn = false;
          this.readonly.cphNmbr = false;
          this.crtNmbr = true;
          this.min = 3;
          this.sec = 179;
          return val;
        }
      }
    },
    computed:{
      chkList:function(){
        let check = false;
        if(this.chkEmail&& this.chkPw && this.chkChkPw
          && this.chkCn&& this.chkCrtn&& this.chkTermsOfUse
          && this.chkPrivacyPolicy && this.chkAdult){
          if(this.form.certNmbr){
            check= true;
          }
        }
        return check;
      },
      timer: function(){
        return this.min + ":" + this.remainingSeconds;
      }

    }
  }

</script>

<style scoped>
  .btn_gray{
    display: block;
    width: 32%;
    height: 40px;
    margin-left: 10px;
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    font-size: 17px;
    color: #fff;
    background: #AAAAAA;
    font-weight: bold;
    cursor: pointer;
  }
  .btn_blue{
    display: block;
    width: 32%;
    height: 40px;
    margin-left: 10px;
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    font-size: 17px;
    color: #fff;
    background: #1d68d4;
    font-weight: bold;
    cursor: pointer;
  }
  .error_msg{
    color:red;
    width: 80%;
    margin: 0 auto 8px auto;
  }
</style>
