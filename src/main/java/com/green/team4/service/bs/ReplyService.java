package com.green.team4.service.bs;

import com.green.team4.vo.bs.Criteria;
import com.green.team4.vo.bs.ReplyVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
    public void insert(ReplyVO replyVO);
    public void modify(ReplyVO replyVO);
    public ReplyVO getOne(Long rNo);
    public List<ReplyVO> getPageList(Criteria criteria, Long uNo);
    public void delete(Long rNo);
    public int getTotal(Criteria criteria);
}
