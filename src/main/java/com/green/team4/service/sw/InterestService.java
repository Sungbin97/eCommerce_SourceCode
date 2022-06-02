package com.green.team4.service.sw;


import com.green.team4.vo.sw.InterestVO;

import java.util.List;

public interface InterestService {

    int readAllCnt(int mno); // 찜목록 개수 가져오기
    int register(InterestVO interestVO); // 장바구니 신규 추가
    List<InterestVO> readAll(int mno, int pageNum); // 장바구니 전체 가져오기
    int remove(int mno, int pno); // 장바구니 삭제
}
