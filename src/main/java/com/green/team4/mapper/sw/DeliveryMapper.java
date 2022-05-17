package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.DeliveryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

    DeliveryVO getOne(int mno, int ono); // 데이터 하나 가져오기(mno, ono 단위로), 특정 사람의 주문 한 건당 배송 한 건 매칭 가정
}
