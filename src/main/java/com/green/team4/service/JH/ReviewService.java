package com.green.team4.service.JH;

import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.ReviewLikeVO;
import com.green.team4.vo.JH.ReviewPageVO;
import com.green.team4.vo.JH.ReviewVO;

import java.util.List;

public interface ReviewService {

    public List<ReviewVO> getReviewList(int pno);

    public void write(ReviewVO rvo);

    public boolean update(ReviewVO rvo);

    public boolean delete(ReviewVO rvo);

    //리뷰 페이징
    public ReviewPageVO getReviewWithPaging(ItemPageCriteria cri);

    public void updateLike(int rno);
    public void updateLikeCancel(int rno);
    //리뷰 좋아요 등록
    public void insertLike(ReviewLikeVO dto);

    public void deleteLike(ReviewLikeVO dto);

    public int checkLike(ReviewLikeVO dto);

}
