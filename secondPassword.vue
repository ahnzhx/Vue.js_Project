<template>
  <!--2차 비밀번호 설정-->
  <div class="box_div f_clear" style="display:block;">
    <div class="se_password_box">
      <h2>{{$t("word.enterSecondaryPW")}}</h2>
      <p>{{$t("message.enterSecondaryPW")}}</p>
      <div class="se_password">
        <input type="password"
               name="secondary_password"
               v-model="secondaryPassword"
               v-validate="'required|scndPwNumeric'"
               :placeholder="$t('word.4figureNm')"
               :data-vv-as="$t('word.secondaryPassword')">
      </div>
        <div class="error_msg2" v-show="errors.has('secondary_password')">{{ errors.first("secondary_password") }}</div>
        <div style='clear:both; overflow: hidden;'></div>

      <div class="se_password">
        <input type="password"
               name="chk_secondary_password"
               v-model="chksecondaryPassword"
               v-validate="'required|chkScndPwNumeric'"
               :placeholder="$t('word.chkSecondaryPassword')"
               :data-vv-as="$t('word.chkSecondaryPassword')">
      </div>
        <div class="error_msg2" v-show="errors.has('chk_secondary_password')">{{ errors.first("chk_secondary_password") }}</div>
        <div style='clear:both; overflow: hidden;'></div>

      <a href="#" class="se_password_btn" @click="registScndryPasswd">{{$t("word.setting")}}</a>
    </div>

    <div style='clear:both;'></div>

  <modal name="setScndPW" class="black_bg_op">
    <div class="pop_up_form">
      <strong>{{$t('word.secondaryPassword')}} {{$t('word.setting')}} {{$t('word.complete')}}</strong>
      <p>{{$t('message.secondaryPasswordRegistSuccess')}}</p>
      <a href="#" class="pop_up_form_btn" @click="closed">{{$t('button.confirm')}}</a>
    </div>
  </modal>
  </div>
</template>

<script>
  import GridLoader from 'vue-spinner/src/GridLoader';

  const scndPwRules =/^([0-9]{4})$/;

    export default {
        name: "cellphone",
      components:{
        GridLoader
      },
        data(){
          return{
            secondaryPassword:'',
            chksecondaryPassword:'',
            loading:'',
            color:'#162334',
            size:'100px',
          }
        },
      methods:{
          registScndryPasswd(){
            this.$validator.validateAll().then((result)=>{
              if(result && this.secondaryPassword == this.chksecondaryPassword){
                if(confirm(this.$t('message.registSecondPassword'))){
                  this.loading = true;
                  this.$modal.show('setScndPW');
                  this.$http.put("/api/v1/certificate/scndPwRegist",{mmbrNmbr: localStorage.getItem("mmbrNmbr"), emlAddr:this.$store.state.email ,scndPswd:this.secondaryPassword}).then((res)=>{
                   this.$store.dispatch('CHECK_MEMBER_INFO');
                  }).catch((err)=>{ })
                }
              }
            })
          },
        closed(e){
          this.$router.push('securitCertification');
        }
      },
      watch:{
        secondaryPassword:{
          handler:function(val, oldval){
            if(val.length===5){
              if(oldval.length === 4){
                this.secondaryPassword = oldval;
              }
            }
          }
        },
        chksecondaryPassword:{
          handler:function(val, oldval){
            if(val.length===5){
              if(oldval.length === 4){
                this.chksecondaryPassword = oldval;
              }
            }
          }
        }
      },
      mounted(){
          this.$validator.extend('scndPwNumeric',{
            validate: value =>{
              return scndPwRules.test(value)
            }
          }),
          this.$validator.extend('chkScndPwNumeric',{
            validate: value =>{
              return scndPwRules.test(value)
            }
          })
      }
    }
</script>

<style scoped>
</style>
