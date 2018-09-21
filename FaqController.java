package com.bitplus.btsapi.rest.v1.serviceCenter;

import com.bitplus.btsapi.common.exception.BizException;
import com.bitplus.btsapi.mapper.TB_BTS_CM0101D;
import com.bitplus.btsapi.mapper.TB_BTS_CM0502M;
import com.bitplus.btsapi.model.CommonCdVo;
import com.bitplus.btsapi.model.FaqVo;
import com.bitplus.btsapi.rest.v1.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * COPYRIGHT ©LINK TREE.
 * class-definition :
 * ========================================
 * | date    |    writer  | desc
 * ========================================
 * | 2018-07-31 |    안소현   | 최초 작성
 **/

@RestController
@RequestMapping("/v1/faq")
public class FaqController extends BaseController {

    @Autowired
    private TB_BTS_CM0101D tb_bts_cm0101D;

    @Autowired
    private TB_BTS_CM0502M tb_bts_cm0502M;

    /**
     * desc : 자주하는질문 목록조회
     * @return
     */
    @GetMapping(value="/getFaqList")
    public ResponseEntity<FaqVo> getFaqList(){
        String grupCd = "FREQ_QUE_SECTN_CD";
        List<CommonCdVo> cdList = null;
        FaqVo vo = null;
       try {
           vo = new FaqVo();
           cdList = tb_bts_cm0101D.getCodeList(grupCd);
           List<FaqVo> faqList = null;
           faqList = tb_bts_cm0502M.getFaqList();
           FaqVo dataVo = null;
           List<FaqVo> descList = null;
           List<FaqVo> dataList = new ArrayList<>();
           for(CommonCdVo cdVo : cdList){
               dataVo = new FaqVo();
               descList = new ArrayList<>();
               dataVo.setFreqQueSectnCd(cdVo.getCd());
               dataVo.setFreqQueSectnNm(cdVo.getCdNm());
               for(FaqVo faqVo : faqList){
                   if(cdVo.getCd().equals(faqVo.getFreqQueSectnCd())){
                       descList.add(faqVo);
                   }
               }
               dataVo.setDescList(descList);
               dataList.add(dataVo);
           }
           vo.setDescList(dataList);
       }catch(BizException e){
           throw new BizException(getMessage("error.systemerror", null));
       }catch(Exception e){
           throw e;
       }
        return new ResponseEntity(vo, HttpStatus.OK);
    }

    @RequestMapping(value="/searchFaq", method=RequestMethod.GET)
    public ResponseEntity<FaqVo> searchFaq(HttpServletRequest request){
        String grupCd = "FREQ_QUE_SECTN_CD";
        List<CommonCdVo> cdList = null;
        FaqVo vo = null;
        FaqVo faqvo = new FaqVo();
        try {
            vo = new FaqVo();
            cdList = tb_bts_cm0101D.getCodeList(grupCd);
            vo.setSchWord(request.getParameter("schWord"));
            List<FaqVo> faqList = tb_bts_cm0502M.searchFaq(vo);
            FaqVo fvo = null;
            List<FaqVo> descList = null;
            List<FaqVo> dtList = new ArrayList<>();

            for(CommonCdVo cdVo : cdList){
                descList = new ArrayList<>();
                fvo = new FaqVo();
                fvo.setFreqQueSectnCd(cdVo.getCd());
                fvo.setFreqQueSectnNm(cdVo.getCdNm());
                for(FaqVo faqVo : faqList){
                    if(cdVo.getCd().equals(faqVo.getFreqQueSectnCd())){
                        descList.add(faqVo);
                    }
                }
                fvo.setDescList(descList);
                dtList.add(fvo);
            }
            faqvo.setDescList(dtList);

        }catch(BizException e){
            throw new BizException(getMessage("error.systemerror", null));
        }catch(Exception e){
            throw e;
        }
        return new ResponseEntity(faqvo, HttpStatus.OK);
    }
}
