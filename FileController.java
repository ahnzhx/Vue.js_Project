package com.bitplus.btsapi.controller;

import com.bitplus.btsapi.common.config.Constant;
import com.bitplus.btsapi.common.exception.BizException;
import com.bitplus.btsapi.model.FileVo;
import com.bitplus.btsapi.rest.v1.BaseController;
import com.bitplus.btsapi.rest.v1.MembersController;
import com.bitplus.btsapi.service.certification.CertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * COPYRIGHT ©LINK TREE.
 * class-definition :
 * ========================================
 * | date    |    wrtier  | desc
 * ========================================
 * | 2018-09-11 |    장상익   | 최초 작성
 **/
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    CertificationService certificationService;

    @Autowired
    FileController fileController;

    public static final Logger log = LoggerFactory.getLogger(MembersController.class);

    /**
     * desc: 비대면 인증 파일 등록
     * @param vo
     * @param bindResult
     * @return
     */
    @RequestMapping(value = "/nonfacing/fileUpload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String fileUpload (@ModelAttribute @Valid FileVo vo, BindingResult bindResult) {

        if(bindResult.hasErrors()){
            throw new ValidationException(getMessage(Constant.FIELD_VALUE_INVALID, null));
        }

        MultipartFile mfIdImg = vo.getIdImg();
        MultipartFile mfHoldingIdImg = vo.getHoldingIdImg();
        String idImgUploadPath=""; //신분증 사진
        String holdingIdImgUploadPath=""; //신분증 들고있는 사진
        String path ="C:\\"+"upload\\"+"test\\";

        String idImgFileNm = fileController.chgSaveNm();
        String holdingIdImgFileNm = fileController.chgSaveNm();
        idImgUploadPath = path+idImgFileNm;
        holdingIdImgUploadPath = path+holdingIdImgFileNm;

        try {
            vo.setFilePath(path);
            mfIdImg.transferTo(new File(idImgUploadPath));
            mfHoldingIdImg.transferTo(new File(holdingIdImgUploadPath));

            //'신분증' db에 넣기
            certificationService.fileInsert(vo);
            //'신분증 들고있는 사진' db에 넣기
            certificationService.holdingIdFileInsert(vo);
        }catch(BizException e){
            throw new BizException(getMessage(Constant.FILE_UPLOAD_FAILED, null));
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(getMessage(Constant.SYSTEM_ERROR_MESSAGE, null));
        }

        return "Success";
    }

    /**
     * desc: 서류제출 안내 공통 파일 등록
     * @param vo
     * @param bindResult
     * @return
     */
    @RequestMapping(value = "/paperInfo/cmFileUpload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String paperInfo (@ModelAttribute @Valid FileVo vo, BindingResult bindResult) {

        if(bindResult.hasErrors()){
            throw new ValidationException(getMessage(Constant.FIELD_VALUE_INVALID, null));
        }

        MultipartFile mfIdImg = vo.getIdImg();
        MultipartFile mfHoldingIdImg = vo.getHoldingIdImg();

        String idImgUploadPath=""; //신분증 사진
        String holdingIdImgUploadPath=""; //신분증 들고있는 사진

        String path ="C:\\"+"upload\\"+"paperInfo\\";
        String idImgFileNm = fileController.chgSaveNm();
        String holdingIdImgFileNm = fileController.chgSaveNm()+"1";
        idImgUploadPath = path+idImgFileNm;
        holdingIdImgUploadPath = path+holdingIdImgFileNm;

        try {
            mfIdImg.transferTo(new File(idImgUploadPath));
            mfHoldingIdImg.transferTo(new File(holdingIdImgUploadPath));
            vo.setFilePath(path);
            //'신분증' db에 넣기
            certificationService.fileInsert(vo);
            //'신분증 들고있는 사진' db에 넣기
            certificationService.holdingIdFileInsert(vo);
        }catch(BizException e){
            throw new BizException(getMessage(Constant.FILE_UPLOAD_FAILED, null));
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(getMessage(Constant.SYSTEM_ERROR_MESSAGE, null));
        }
        return "Success";
    }

    /**
     * desc: 서류제출 안내 기타 파일 등록
     * @param vo
     * @return
     */
    @RequestMapping(value = "/paperInfo/etcFileUpload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String etcFileUpload (@ModelAttribute @Valid FileVo vo, BindingResult bindResult) {

        if(bindResult.hasErrors()){
            throw new ValidationException(getMessage(Constant.FIELD_VALUE_INVALID, null));
        }

        MultipartFile mfEtcImg = vo.getEtcImg();
        String etcImgUploadPath =""; //기타 사진
        String path ="C:\\"+"upload\\"+"paperInfo\\";
        vo.setFilePath(path);
        try {
            String etcImgFileNm = fileController.chgSaveNm()+"2";
            etcImgUploadPath = path+etcImgFileNm;
            mfEtcImg.transferTo(new File(etcImgUploadPath));
            certificationService.etcImgFileInsert(vo);
        }catch(BizException e){
            throw new BizException(getMessage(Constant.FILE_UPLOAD_FAILED, null));
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(getMessage(Constant.SYSTEM_ERROR_MESSAGE, null));
        }
        return "Success";
    }

    public String chgSaveNm(){
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDT = dayTime.format(new Date(time));

        return strDT;
    }
}
