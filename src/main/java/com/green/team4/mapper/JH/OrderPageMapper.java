package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.OrderPageItemVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderPageMapper {
    
    //주문 상품 정보
    public OrderPageItemVO getProductsInfo(int pno);


}
