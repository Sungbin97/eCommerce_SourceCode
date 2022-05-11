package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.DeliveryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

    DeliveryVO getOne(int ono); // 데이터 하나 가져오기(ono 단위로), 주문 한 건당 배송 한 건 매칭 가정
}
