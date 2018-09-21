<template>
  <!--비대면 인증-->
  <div class="box_div f_clear">
    <div class="identity_box">
      <div style="width: 300px; text-align: left; ">
        <img v-if="!idImgData" src="../../../assets/image/sub/identity_icon.png" style="height:200px;" alt="신분증">
        <img v-else :src="idImgData" style="height:200px;">
        <input id="idImg" class="file_btn" type="file" accept="image/*" @change="idImgChanged" style="display: none">
        <button class="file_btn" @click="leadToInput('idImg')">{{$t("button.selectFile")}}</button>
      </div>
      <div style="width: 300px; text-align: left;">
        <img v-if="!holdingIdImgData" src="../../../assets/image/sub/identity_person_icon.png" style="height:200px;" alt="신분증을 들고있는 사진">
        <img v-else :src="holdingIdImgData" style="height:200px;">
        <input id="holdingIdImg" type="file" accept="image/*" @change="holdingIdImgChanged" style="display: none">
        <button class="file_btn" @click="leadToInput('holdingIdImg')">{{$t("button.selectFile")}}</button>
      </div>
    </div>
    <a href="#" class="identity_btn" @click="fileInsert">{{$t("word.apply")}}</a>
    <div style='clear:both;'></div>
  </div>
</template>

<script>
    export default {
      name: "nonfacing",
      data(){
        return{
          idImg: null,       //신분증 사진
          holdingIdImg:null, //신분증 들고있는 본인사진
          mmbrNmbr:localStorage.getItem("mmbrNmbr"),
          idImgData:"",
          holdingIdImgData:""
        }
      },
      methods: {
        idImgChanged(event) {
          this.idImg = event.target.files[0];
          var reader = new FileReader();
          reader.onload = (e) => {
            this.idImgData = e.target.result;
          }
          reader.readAsDataURL(this.idImg);
        },
        holdingIdImgChanged(event) {
          this.holdingIdImg = event.target.files[0];
          var reader = new FileReader();
          reader.onload = (e) => {
            this.holdingIdImgData = e.target.result;
          }
          reader.readAsDataURL(this.holdingIdImg);

        },
        fileInsert() {
          let formData = new FormData();
          formData.append('idImg', this.idImg);
          formData.append('holdingIdImg', this.holdingIdImg);
          formData.append('mmbrNmbr', this.mmbrNmbr);

          if (!this.idImg || !this.holdingIdImg) {
            alert("신분증과 신분증을 들고있는 사진 모두 등록해주세요.");
          }

          this.$http.post("/api/file/nonfacing/fileUpload", formData,
            {headers: {'Content-Type': 'multipart/form-data'}}).then((res) => {
            alert("파일이 등록되었습니다.");
          })
        },
        leadToInput(id) {
          $('#'+id).trigger('click');
        }
      }
    }
</script>

<style scoped>
</style>
