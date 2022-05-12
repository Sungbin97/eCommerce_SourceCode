package com.green.team4.service.JH;

import com.green.team4.vo.JH.ReviewVO;

import java.util.List;

public interface ReviewService {

    public List<ReviewVO> getReviewList(int pno);

    public void write(ReviewVO rvo);

    public boolean update(ReviewVO rvo);

    public boolean delete(ReviewVO rvo);



}
