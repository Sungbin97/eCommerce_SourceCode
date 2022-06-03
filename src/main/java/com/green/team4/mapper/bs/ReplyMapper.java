package com.green.team4.mapper.bs;

import com.green.team4.vo.bs.ReplyVO;
import com.green.team4.vo.bs.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    public void insert(ReplyVO vo);
    public void modify(ReplyVO vo);
    public ReplyVO readOne(Long uNo);
    public List<ReplyVO> replyList();
    public void delete(Long rNo);
    public List<ReplyVO> getPageList(@Param("criteria") Criteria criteria,
                                     @Param("uNo") Long uNo);
    public int getTotalCount(Criteria criteria);
    // 전체 게시글 수를 구하기 위한 메서드
}
