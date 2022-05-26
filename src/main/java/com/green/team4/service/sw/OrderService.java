package com.green.team4.service.sw;

import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.OrderVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> readAll(int mno); // 주문목록 전체 가져오기
    OrderVO readOne(String ono); // 주문목록 하나 가져오기
    List<OrderVO> readAllAdmin(); // 관리자 전체목록 가져오기
    int modifyItem(OrderItemVO orderItemVO); // 주문상품 업데이트
    int modifyStatus(OrderVO orderVO); // 주문상태 업데이트
    int register(OrderVO orderVO); // 취소/반품/교환 새주문서 등록
}
