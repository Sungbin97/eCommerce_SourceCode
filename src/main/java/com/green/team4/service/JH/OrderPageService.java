package com.green.team4.service.JH;

import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.OrderPageItemVO;

import java.util.List;

public interface OrderPageService {
//    주문정보
    public List<OrderPageItemVO> getProductListInfo(List<OrderPageItemVO> orders);

//    주문하기
    public void order(DBOrderVO vo);
}
