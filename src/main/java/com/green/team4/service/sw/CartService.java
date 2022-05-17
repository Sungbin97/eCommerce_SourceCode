package com.green.team4.service.sw;

import com.green.team4.vo.sw.CartVO;

import java.util.List;

public interface CartService {

    List<CartVO> readAll(int mno); // 장바구니 전체 가져오기
    int remove(int cno); // 장바구니 삭제
}
