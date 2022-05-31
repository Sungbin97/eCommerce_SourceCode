package com.green.team4.service.sw;


import com.green.team4.vo.sw.InterestVO;

import java.util.List;

public interface InterestService {

    int register(InterestVO interestVO); // 장바구니 신규 추가
    List<InterestVO> readAll(int mno); // 장바구니 전체 가져오기
    int remove(int mno, int pno); // 장바구니 삭제
}
