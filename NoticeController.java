package com.bitplus.btsapi.rest.v1.serviceCenter;

import com.bitplus.btsapi.common.exception.BizException;
import com.bitplus.btsapi.mapper.TB_ADM_OP0101M;
import com.bitplus.btsapi.mapper.TB_BTS_CM0101D;
import com.bitplus.btsapi.model.CommonCdVo;
import com.bitplus.btsapi.model.NoticeVo;
import com.bitplus.btsapi.rest.v1.BaseController;
import com.bitplus.btsapi.rest.v1.MembersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/notice")
public class NoticeController extends BaseController {

    public static final Logger log = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private TB_ADM_OP0101M tb_adm_op0101M;

    @Autowired
    private TB_BTS_CM0101D tb_bts_cm0101D;

    @RequestMapping(value = "/getNList", method = RequestMethod.GET)
    public ResponseEntity<NoticeVo> getNList(HttpServletRequest request){

        List<CommonCdVo> cdList = null;
        try {
            NoticeVo nvo = new NoticeVo();
            nvo.setPage(request.getParameter("page"));
            nvo.setSchWord(request.getParameter("schWord"));
            List<NoticeVo> notcList = tb_adm_op0101M.getNList(nvo);
            NoticeVo rtVo = new NoticeVo();
            rtVo.setDataList(notcList);
            return new ResponseEntity(rtVo, HttpStatus.OK);
        }catch (BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch(Exception e){
            throw e;
        }

    }

    @RequestMapping(value = "/getYList", method = RequestMethod.GET)
    public ResponseEntity<NoticeVo> getYList(HttpServletRequest request){
        String grupCd = "NOTC_SECTN_CD";
        List<CommonCdVo> cdList = null;
        try {
            cdList = tb_bts_cm0101D.getCodeList(grupCd);
            NoticeVo nvo = new NoticeVo();
            nvo.setPage(request.getParameter("page"));
            nvo.setSchWord(request.getParameter("schWord"));
            log.info(nvo.getSchWord());
            List<NoticeVo> notcList = tb_adm_op0101M.getYList(nvo);

            NoticeVo dataVo = null;
            NoticeVo rtVo = new NoticeVo();
            List<NoticeVo> descList = null;
            List<NoticeVo> dataList = new ArrayList<>();

            for(CommonCdVo cdVo : cdList){
                dataVo = new NoticeVo();
                descList = new ArrayList<>();
                dataVo.setNotcSectnNm(cdVo.getCdNm());
                dataVo.setNotcSectnCd(cdVo.getCd());

                for(NoticeVo noticeVo : notcList ){
                    if(cdVo.getCd().equals(noticeVo.getNotcSectnCd())){
                        descList.add(noticeVo);
                    }
                }
                dataVo.setDescList(descList);
                dataList.add(dataVo);
                rtVo.setDataList(dataList);
            }
            return new ResponseEntity(rtVo, HttpStatus.OK);

        }catch (BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch (Exception e){
            throw e;
        }

    }

    @RequestMapping(value = "/getYListOnly", method = RequestMethod.GET)
    public ResponseEntity<NoticeVo> getYListOnly(HttpServletRequest request){

        try {
            NoticeVo nvo = new NoticeVo();
            nvo.setNotcSectnCd(request.getParameter("notcSectnCd"));
            List<NoticeVo> notcList = tb_adm_op0101M.getYListOnly(nvo);
            NoticeVo rtVo = new NoticeVo();
            rtVo.setDataList(notcList);
            return new ResponseEntity(rtVo, HttpStatus.OK);
        }catch (BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "/getNoticeMaxPage", method = RequestMethod.GET)
    public ResponseEntity<NoticeVo> getNoticeMaxPage(HttpServletRequest request){
       NoticeVo nvo = new NoticeVo();
       try {
           nvo.setSchWord(request.getParameter("schWord"));
           List<NoticeVo> maxPage = tb_adm_op0101M.getNoticeMaxPage(nvo);
           nvo.setDataList(maxPage);
           return new ResponseEntity(nvo, HttpStatus.OK);
       }catch (BizException e){
           throw new BizException(getMessage("error.systemerror", null));
       }catch (Exception e){
           throw e;
       }

    }

    @RequestMapping(value = "/updateSrchCnt", method = RequestMethod.GET)
    public void updateSrchCnt(HttpServletRequest request){

        NoticeVo nvo = new NoticeVo();
        try {
            nvo.setNotcSeq(request.getParameter("notcSeq"));
            tb_adm_op0101M.updateSrchCnt(nvo);
        }catch (BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch (Exception e){
            throw e;
        }

    }

    /*noticeView*/
    @RequestMapping(value = "/getNoticeView", method = RequestMethod.GET)
    public NoticeVo getNoticeView(HttpServletRequest request){

        String nvo = request.getParameter("notcSeq");
        try {
            NoticeVo noticeVo = tb_adm_op0101M.getNoticeView(nvo);
            return noticeVo;
        }catch (BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch(Exception e){
            throw e;
        }

    }

}
