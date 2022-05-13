package com.green.team4.service.sw;


import com.green.team4.vo.sw.InterestVO;

import java.util.List;

public interface InterestService {

    List<InterestVO> readAll(int mno); // 장바구니 전체 가져오기
    int remove(int itrNo); // 장바구니 삭제
}
