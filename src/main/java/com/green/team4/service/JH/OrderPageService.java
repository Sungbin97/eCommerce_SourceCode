package com.green.team4.service.JH;

import com.green.team4.vo.JH.OrderPageItemVO;

import java.util.List;

public interface OrderPageService {

    public List<OrderPageItemVO> getProductsInfo(List<OrderPageItemVO> orders);


}
