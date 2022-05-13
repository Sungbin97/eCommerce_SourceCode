package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.ReviewVO;
import com.green.team4.vo.JH.UpdateReplyVO;
import com.green.team4.vo.JH.UpdaterReviewCntVO;
import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {


    public List<ReviewVO> getReviewList(int pno);
    public int getReview(int pno);
    public void write(ReviewVO rvo);
    public int update(ReviewVO rvo);

    public int delete(int rvo);

    //평점 구하기
    public Double getRatingAvg(int pno);

    // 평점 업데이트하기
    public int updateRating(UpdateReplyVO urvo);

    //리뷰 갯수 구하기
    public int getReviewsCount(int pno);
    public int updateReviewsCount(UpdaterReviewCntVO urcvo);

    //리뷰 페이징
    public List<ReviewVO> getReviewListWithPaging(ItemPageCriteria cri);
}



