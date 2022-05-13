package com.green.team4.service.sw;

import com.green.team4.vo.sw.OrderVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> readAll(int mno); // 주문목록 전체 가져오기
    OrderVO readOne(int ono); // 주문목록 하나 가져오기
    int modify(OrderVO orderVO);
}
