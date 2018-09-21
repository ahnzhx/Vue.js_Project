package com.bitplus.btsapi.model;

import java.util.List;

public class NoticeVo {

    private String notcSeq;    /*순번*/
    private String notcTit;    /*공지사항제목*/
    private String notcCtnt;   /*공지사항내용*/
    private String rgDt;       /*등록일자*/
    private String edtDt;      /*수정일자*/
    private String rgt;         /*등록자*/
    private String edr;         /*수정자*/
    private String srchCnt;    /*조회수*/
    private String rn;
    private String page;
    private String maxPage;
    private String schWord;     /*검색어*/
    private String totalRows;   /*전체 행 갯수*/
    private String notcYn;      /*공지여부*/
    private String notcSectnCd; /*공지사항구분코드*/
    private String notcSectnNm;
    private List<NoticeVo> descList;
    private List<NoticeVo> dataList;

    public List<NoticeVo> getDataList() { return dataList; }

    public void setDataList(List<NoticeVo> dataList) { this.dataList = dataList; }

    public List<NoticeVo> getDescList() { return descList; }

    public void setDescList(List<NoticeVo> descList) { this.descList = descList; }

    public String getNotcSectnNm() { return notcSectnNm; }

    public void setNotcSectnNm(String notcSectnNm) { this.notcSectnNm = notcSectnNm; }

    public String getNotcSectnCd() { return notcSectnCd; }

    public void setNotcSectnCd(String notcSectnCd) { this.notcSectnCd = notcSectnCd; }

    public String getNotcSeq() {
        return notcSeq;
    }

    public void setNotcSeq(String notcSeq) {
        this.notcSeq = notcSeq;
    }

    public String getNotcTit() {
        return notcTit;
    }

    public void setNotcTit(String notcTit) {
        this.notcTit = notcTit;
    }

    public String getNotcCtnt() { return notcCtnt; }

    public void setNotcCtnt(String notcCtnt) {
        this.notcCtnt = notcCtnt;
    }

    public String getRgDt() {
        return rgDt;
    }

    public void setRgDt(String rgDt) {
        this.rgDt = rgDt;
    }

    public String getEdtDt() {
        return edtDt;
    }

    public void setEdtDt(String edtDt) {
        this.edtDt = edtDt;
    }

    public String getRgt() {
        return rgt;
    }

    public void setRgt(String rgt) {
        this.rgt = rgt;
    }

    public String getEdr() {
        return edr;
    }

    public void setEdr(String edr) {
        this.edr = edr;
    }

    public String getSrchCnt() {
        return srchCnt;
    }

    public void setSrchCnt(String srchCnt) {
        this.srchCnt = srchCnt;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    public String getSchWord() {
        return schWord;
    }

    public void setSchWord(String schWord) {
        this.schWord = schWord;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    public String getNotcYn() {
        return notcYn;
    }

    public void setNotcYn(String notcYn) {
        this.notcYn = notcYn;
    }
}
