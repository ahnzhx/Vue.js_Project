package com.bitplus.btsapi.service.certification.impl;

import com.bitplus.btsapi.common.config.Constant;
import com.bitplus.btsapi.common.exception.BizException;
import com.bitplus.btsapi.common.exception.NotFoundException;
import com.bitplus.btsapi.common.util.crypto.Sha256;
import com.bitplus.btsapi.common.util.lang.AuthKey;
import com.bitplus.btsapi.controller.FileController;
import com.bitplus.btsapi.mapper.*;
import com.bitplus.btsapi.model.*;
import com.bitplus.btsapi.rest.v1.MembersController;
import com.bitplus.btsapi.service.certification.CertificationService;
import com.bitplus.btsapi.service.email.EmailSendService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CertificationServiceimpl implements CertificationService{

    public static final Logger log = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    TB_BTS_MB0101M tb_bts_mb0101M;

    @Autowired
    TB_BTS_MB0202M tb_bts_mb0202M;

    @Autowired
    TB_BTS_MB0203M tb_bts_mb0203M;

    @Autowired
    TB_BTS_MB0208D tb_bts_mb0208D;

    @Autowired
    TB_BTS_MB0204M tb_bts_mb0204M;

    @Autowired
    TB_BTS_MB0205M tb_bts_mb0205M;

    @Autowired
    TB_BTS_CM0301M tb_bts_cm0301M;

    @Autowired
    TB_BTS_CM0401M tb_bts_cm0401M;

    @Autowired
    FileController fileController;

    @Override
    @Transactional
    public void sendEmail(EmailCertificateVo vo) {

        //인증 요청 정보 등록
        tb_bts_mb0203M.regist(vo);

        //메일 인증키가 존재하는 지 확인
        EmailCertificateVo authInfo = tb_bts_mb0202M.getEmailCerticateKey(vo);

        if(authInfo == null){//인증키가 존재하지 않으면 인증키 생성
            //이메일 전송
            authInfo.setEmlAddr(vo.getEmlAddr());
            authInfo.setEmlAthk(new AuthKey().getKey(Constant.EMAIL_AUTH_KEY_SIZE, false));
            authInfo.setRegistYn("N");
        }
        //이메일 전송
        authInfo.setSubject(_getMessage(Constant.EMAIL_AUTH_SUBJECT, null));
        emailSendService.sendCertificationEmail(authInfo);

    }

    /**
     * desc: 이메일 인증처리
     * author: 장상익
     * @param vo
     */
    @Override
    @Transactional
    public void emailCertification(EmailCertificateVo vo) {

        //이미 인증을 받았는지 확인
        MemberLevelVo memberLevelVo = new MemberLevelVo();
        memberLevelVo.setMmbrNmbr(vo.getMmbrNmbr());
        memberLevelVo.setEmlAddr(vo.getEmlAddr());
        memberLevelVo.setScrtLv(1);
        MemberLevelVo result = tb_bts_mb0208D.getInfo(memberLevelVo);
        if(result != null){
            throw new BizException(_getMessage(Constant.ALREADY_CERTIFICATE, new String[]{"email"}));
        }

        //인증키와 이메일을 확인
        EmailCertificateVo authInfo = tb_bts_mb0202M.getEmailCerticateKey(vo);
        if(StringUtils.isEmpty(authInfo.getEmlAthk())){
            throw new NotFoundException(_getMessage(Constant.MEMBER_NOT_FOUND,null));
        }
        _updateMemberLevel(vo.getEmlAddr(), Constant.SECRET_LEVEL_1, Constant.LEVEL_1, Constant.GRADE_CODE_01);
    }

    /**
     * 휴대폰 본인인증처리
     * @param vo
     */
    @Override
    @Transactional
    public void cellphoneCertification(CellPhoneCertificateVo vo) {
        //휴대폰 인증내역 등록
        tb_bts_mb0204M.regist(vo);

        //회원정보 수정
        MembersVo membersVo = new MembersVo();
        membersVo.setMmbrNmbr(vo.getMmbrNmbr());
        membersVo.setMmbrNm(vo.getName());
        membersVo.setDtbt(vo.getDob());
        tb_bts_mb0101M.updateMemberBaseInfo(membersVo);

    }

    @Override
    public void secondaryPasswordRegist(MembersVo vo) {

        //2차비밀번호 입력
        vo.setScndPswd(Sha256.encrypt(vo.getScndPswd()));
        tb_bts_mb0101M.updateScndPassWd(vo);

        //회원레벨 변경
        _updateMemberLevel(vo.getEmlAddr(), Constant.SECRET_LEVEL_2, Constant.LEVEL_2, Constant.GRADE_CODE_02);
    }

    /**
     * desc: 구글OTP 인증생성
     */
    @Override
    @Transactional
    public GoogleOTPCertificateVo googleOtpCertificationCreate(GoogleOTPCertificateVo vo) {

        //이미 발급받았는 지 확인
        GoogleOTPCertificateVo chkVo = tb_bts_mb0205M.getKey(vo);

        if(chkVo == null){
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            final GoogleAuthenticatorKey key = gAuth.createCredentials();

            String qrCodeUrl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("비트플러스", vo.getEmail(), key);
            vo.setKey(key.getKey());
            vo.setQrCodeUrl(qrCodeUrl);
            vo.setScratchCodes(key.getScratchCodes());
            vo.setVerificationCode(key.getVerificationCode());
            vo.setRecoveryCode(new AuthKey().getKey(16, false));

            //OTP 인증키 저장
            tb_bts_mb0205M.regist(vo);
            return vo;

        }else{
            return chkVo;
        }
    }

    /**
     * desc: 구글 OTP 인증 처리
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public boolean googleOtpCertificatetionVerify(GoogleOTPCertificateVo vo) {
        //인증키 조회
        String secretKey = tb_bts_mb0205M.getKey(vo).getKey();

        int password = Integer.parseInt(vo.getPassword());
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(secretKey, password);

        //인증 성공시
        if(isCodeValid){
            if("Y".equals(vo.getRegistYn())){//첫 인증 등록시 레벨 변경
                _updateMemberLevel(vo.getEmail(),Constant.SECRET_LEVEL_3,Constant.LEVEL_3,Constant.GRADE_CODE_03);
            }
        }else{
            throw new BizException(_getMessage(Constant.SYSTEM_ERROR_MESSAGE, null));
        }

        return isCodeValid;

    }

    /**
     * desc : 2차비밀번호 인증
     * @param vo
     * @return
     */
    @Override
    public boolean scndPwVerify(GoogleOTPCertificateVo vo) {
        //otp 일치 확인
        String secretKey = tb_bts_mb0205M.getKey(vo).getKey();
        int otpNum = Integer.parseInt(vo.getOtpNum());
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isOtpValid = gAuth.authorize(secretKey, otpNum);

        //2차 비밀번호 일치 확인
        String scndPswd = tb_bts_mb0205M.scndPwVerify(vo).getScndPswd();
        String chkScndPswd = Sha256.encrypt(vo.getPassword());

        boolean isCodeValid = false;
        if(scndPswd.equals(chkScndPswd) && isOtpValid ){
            isCodeValid = true;
        }else{
            isCodeValid = false;
            throw new BizException(_getMessage(Constant.SYSTEM_ERROR_MESSAGE, null));
        }
        return isCodeValid;
    }

    /**
     * desc: '신분증' db에 넣기
     * @param vo
     */
    @Override
    public void fileInsert(FileVo vo) {
        int activeIndex ;
        activeIndex = vo.getActiveIndex();

        FileVo filevo = new FileVo();
        filevo.setMmbrNmbr(vo.getMmbrNmbr());
        /*비대면인증파일 구분코드*/
        filevo.setNnfcngAthFileSectnCd("01");

        /*서류제출 구분코드*/
        switch (activeIndex){
            case 1:
                filevo.setDocSubSectnCd("01");
                break;
            case 2:
                filevo.setDocSubSectnCd("02");
                break;
            case 3:
                filevo.setDocSubSectnCd("03");
                break;
            case 4:
                filevo.setDocSubSectnCd("04");
                break;
            case 5:
                filevo.setDocSubSectnCd("05");
                break;
            case 6:
                filevo.setDocSubSectnCd("06");
                break;
            case 7:
                filevo.setDocSubSectnCd("07");
                break;
            case 8:
                filevo.setDocSubSectnCd("08");
                break;
        }

        /*서류제출파일 구분코드*/
        filevo.setDocSubFileSectnCd("01");
        filevo.setOrgnlFileNm(vo.getIdImg().getOriginalFilename());
        filevo.setFilePath(vo.getFilePath());

        /*파일 경로 지정하기*/
        filevo.setFilePath(vo.getFilePath());

        /*saveFilNm을 millis로 저장하기*/
        String saveFileNm = fileController.chgSaveNm();
        filevo.setSaveFileNm(saveFileNm);

        if(activeIndex == 0){
            tb_bts_cm0301M.fileInsert(filevo);
        }else{
            tb_bts_cm0401M.fileInsert(filevo);
        }

    }

    /**
     * desc: '신분증 들고있는 사진' db에 넣기
     * @param vo
     */
    @Override
    public void holdingIdFileInsert(FileVo vo) {
        int activeIndex;
        activeIndex = vo.getActiveIndex();
        FileVo filevo = new FileVo();
        filevo.setMmbrNmbr(vo.getMmbrNmbr());
        /*비대면인증파일 구분코드*/
        filevo.setNnfcngAthFileSectnCd("02");
        /*서류제출파일 구분코드*/
        filevo.setDocSubFileSectnCd("02");

        /*서류제출 구분코드*/
        switch (activeIndex){
            case 1:
                filevo.setDocSubSectnCd("01");
                break;
            case 2:
                filevo.setDocSubSectnCd("02");
                break;
            case 3:
                filevo.setDocSubSectnCd("03");
                break;
            case 4:
                filevo.setDocSubSectnCd("04");
                break;
            case 5:
                filevo.setDocSubSectnCd("05");
                break;
            case 6:
                filevo.setDocSubSectnCd("06");
                break;
            case 7:
                filevo.setDocSubSectnCd("07");
                break;
            case 8:
                filevo.setDocSubSectnCd("08");
                break;
        }

        filevo.setOrgnlFileNm(vo.getHoldingIdImg().getOriginalFilename());


        /*파일 경로 지정하기*/
        filevo.setFilePath(vo.getFilePath());

        /*saveFilNm을 millis로 저장하기*/
        String saveFileNm = fileController.chgSaveNm();
        filevo.setSaveFileNm(saveFileNm);

        if(activeIndex == 0){
            tb_bts_cm0301M.fileInsert(filevo);
        }else{
            tb_bts_cm0401M.fileInsert(filevo);
        }

    }

    /**
     * desc: '기타 서류 사진' db에 넣기
     * @param vo
     */
    @Override
    public void etcImgFileInsert(FileVo vo) {
        int activeIndex;
        activeIndex = vo.getActiveIndex();
        FileVo filevo = new FileVo();
        filevo.setMmbrNmbr(vo.getMmbrNmbr());

        /*서류제출파일 구분코드*/
        switch (activeIndex){
            case 1:
                filevo.setDocSubFileSectnCd("03");
                break;
            case 2:
                filevo.setDocSubFileSectnCd("04");
                break;
            case 3:
                filevo.setDocSubFileSectnCd("05");
                break;
            case 4:
                filevo.setDocSubFileSectnCd("06");
                break;
            case 5:
                filevo.setDocSubFileSectnCd("06");
                break;
        }

        /*서류제출 구분코드*/
        switch (activeIndex){
            case 1:
                filevo.setDocSubSectnCd("01");
                break;
            case 2:
                filevo.setDocSubSectnCd("02");
                break;
            case 3:
                filevo.setDocSubSectnCd("03");
                break;
            case 4:
                filevo.setDocSubSectnCd("04");
                break;
            case 5:
                filevo.setDocSubSectnCd("05");
                break;
        }

        filevo.setOrgnlFileNm(vo.getEtcImg().getOriginalFilename());

        /*파일 경로 지정하기*/
        filevo.setFilePath(vo.getFilePath());

        /*saveFilNm을 millis로 저장하기*/
        String saveFileNm = fileController.chgSaveNm();
        filevo.setSaveFileNm(saveFileNm);

        tb_bts_cm0401M.fileInsert(filevo);

    }


    /**
     * desc: 메세지 처리
     * @param code
     * @param args
     * @return
     */
    private String _getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * desc: 회원 레벨 처리
     * @param email
     * @param secretLevel
     * @param level
     * @param gradeCd
     */
    private void _updateMemberLevel(String email, int secretLevel, String level, String gradeCd){

        //회원레벨을 인증정보 등록
        MemberLevelVo memberLevelVo = new MemberLevelVo();
        memberLevelVo.setEmlAddr(email);
        memberLevelVo.setScrtLv(secretLevel);
        memberLevelVo.setAthYn("Y");
        memberLevelVo.setScrtGrdCd(gradeCd);
        tb_bts_mb0208D.regist(memberLevelVo);

        //회원 레벨 변경
        MembersVo membersVo = new MembersVo();
        membersVo.setEmlAddr(email);
        membersVo.setScrtAthLv(level);
        tb_bts_mb0101M.updateMemberLevel(membersVo);
    }

}
