package com.green.team4.service.bs;

import com.green.team4.vo.bs.Criteria;
import com.green.team4.vo.bs.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface ReplyService {
    public void insert(ReplyVO replyVO);
    public void modify(ReplyVO replyVO);
    public ReplyVO getOne(Long rno);
    public List<ReplyVO> getPageList(Criteria criteria, Long uNo);
    public void delete(Long rNo);
    public int getTotal(Criteria criteria);
}
