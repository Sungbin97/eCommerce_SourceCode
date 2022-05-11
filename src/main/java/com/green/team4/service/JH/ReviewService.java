package com.green.team4.service.JH;

import com.green.team4.vo.JH.ReviewVO;

import java.util.List;

public interface ReviewService {

    public List<ReviewVO> getReviewList(int p_no);

    public void write(ReviewVO rvvo);

    public boolean update(ReviewVO rvvo);

    public boolean delete(int rv_no);
}
