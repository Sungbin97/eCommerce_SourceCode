package com.green.team4.service.sw;



import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.ReviewPageVO;
import com.green.team4.vo.sw.ReviewMpVO;
import com.green.team4.vo.sw.SearchVO;

import java.util.List;

public interface ReviewMpService {

    int register(ReviewMpVO reviewMpVO); // 리뷰 신규 등록
    //List<ReviewMpVO> readAllByPno(int pno); // 리뷰 전체 가져오기(pno 단위)
    List<ReviewMpVO> readAllByMno(int mno); // 리뷰 전체 가져오기(mno 단위)
    List<ReviewMpVO> readAllByMnoSearch(SearchVO searchVO); // 리뷰 전체 가져오기(mno 단위)
    ReviewMpVO readOneByRno(int rno); // 리뷰 하나 가져오기 (rno 단위)
    int modify(ReviewMpVO reviewMpVO); // 리뷰 수정
    int remove(ReviewMpVO reviewMpVO); // 리뷰 삭제 (rno 단위)

    ReviewPageVO readAllByPno(ItemPageCriteria cri);


}
