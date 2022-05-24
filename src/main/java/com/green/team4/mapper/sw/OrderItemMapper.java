package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.OrderItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    List<OrderItemVO> getAll(String ono); // 데이터 전체 가져오기(ono 단위로)
    OrderItemVO getOne(int oINo); // 데이터 하나 가져오기(orderItemNo 단위로)
    int update(OrderItemVO orderItemVO); // 주문 상품 중 하나 수정(취소/환불/교환,배송 상태만 업데이트 가능)
}
