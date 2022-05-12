package com.green.team4.service.JH;

import com.green.team4.mapper.JH.ReviewMapper;
import com.green.team4.vo.JH.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewVO> getReviewList(int pno) {
        return reviewMapper.getReviewList(pno);
    }

    @Override
    public void write(ReviewVO rvvo) {
        reviewMapper.write(rvvo);

    }

    @Override
    public boolean update(ReviewVO rvvo) {
        return reviewMapper.update(rvvo)==1;
    }

    @Override
    public boolean delete(int rv_no) {
        return reviewMapper.delete(rv_no)==1;
    }
}
