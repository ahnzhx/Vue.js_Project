package com.bitplus.btsapi.mapper;

import com.bitplus.btsapi.model.FaqVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TB_BTS_CM0502M {
    List<FaqVo> getFaqList();
    List<FaqVo> searchFaq(FaqVo vo);
}
