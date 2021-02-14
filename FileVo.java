package com.bitplus.btsapi.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileVo {
    private String mmbrNmbr;                //회원번호
    private String nnfcngAthFileSectnCd;    //비대면인증파일구분코드
    private String filePath;                //파일경로
    private String orgnlFileNm;             //원본파일명
    private String saveFileNm;              //저장파일명
    private String docSubSectnCd;           //서류제출구분코드
    private String docSubFileSectnCd;       //서류제출파일구분코드
    private MultipartFile idImg;            //신분증
    private MultipartFile holdingIdImg;     //신분증을 들고있는 사진
    private MultipartFile etcImg;           //기타 사진
    private int activeIndex;                //탭 이동 activeIndex
    private String rgDt;
    private String edtDt;
    private String rgt;
    private String edr;

}
