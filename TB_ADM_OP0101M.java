package com.bitplus.btsapi.mapper;

import com.bitplus.btsapi.model.NoticeVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TB_ADM_OP0101M {
    List<NoticeVo> getNList(NoticeVo nvo);

    List<NoticeVo> getYList(NoticeVo nvo);

    List<NoticeVo> getYListOnly(NoticeVo nvo);

    List<NoticeVo> getNoticeMaxPage(NoticeVo nvo);

    void updateSrchCnt(NoticeVo nvo);

    NoticeVo getNoticeView(String nvo);
}
