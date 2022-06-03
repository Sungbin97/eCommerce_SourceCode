package com.green.team4.service.bs;

import com.green.team4.mapper.bs.ReplyMapper;
import com.green.team4.vo.bs.Criteria;
import com.green.team4.vo.bs.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyMapper replyMapper;

    @Override
    public void insert(ReplyVO replyVO) {
        replyMapper.insert(replyVO);
    }

    @Override
    public void modify(ReplyVO replyVO) {
        replyMapper.modify(replyVO);
    }

    @Override
    public ReplyVO getOne(Long rNo) {
        return replyMapper.readOne(rNo);
    }

    @Override
    public List<ReplyVO> getPageList(Criteria criteria,Long uNo) {
        return replyMapper.getPageList(criteria,uNo);
    }

    @Override
    public void delete(Long rNo) {
        replyMapper.delete(rNo);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return replyMapper.getTotalCount(criteria);
    }
}
