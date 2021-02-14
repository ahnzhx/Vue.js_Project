package com.bitplus.btsapi.model;

import java.util.List;

public class FaqVo {

    private String freqQueSeq;    /*순번*/
    private String freqQueSectnNm;        /*구분명*/
    private String queNm;          /*질문명*/
    private String anserCtnt;      /*답변내용*/
    private String freqQueSectnCd;
    private String schWord;
    private List<FaqVo> descList;

    public String getSchWord() { return schWord; }

    public void setSchWord(String schWord) { this.schWord = schWord; }

    public List<FaqVo> getDescList() { return descList; }

    public void setDescList(List<FaqVo> descList) { this.descList = descList; }

    public String getFreqQueSectnCd() {
        return freqQueSectnCd;
    }

    public void setFreqQueSectnCd(String freqQueSectnCd) {
        this.freqQueSectnCd = freqQueSectnCd;
    }

    public String getFreqQueSeq() {
        return freqQueSeq;
    }

    public void setFreqQueSeq(String freqQueSeq) {
        this.freqQueSeq = freqQueSeq;
    }

    public String getFreqQueSectnNm() {
        return freqQueSectnNm;
    }

    public void setFreqQueSectnNm(String freqQueSectnNm) {
        this.freqQueSectnNm = freqQueSectnNm;
    }

    public String getQueNm() {
        return queNm;
    }

    public void setQueNm(String queNm) {
        this.queNm = queNm;
    }

    public String getAnserCtnt() {
        return anserCtnt;
    }

    public void setAnserCtnt(String anserCtnt) {
        this.anserCtnt = anserCtnt;
    }
}
