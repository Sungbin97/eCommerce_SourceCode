package com.green.team4.service.JH;

import com.green.team4.mapper.JH.OrderPageMapper;
import com.green.team4.vo.JH.OrderPageItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderPageServiceImpl implements OrderPageService {

    @Autowired
    private OrderPageMapper orderPageMapper;

    @Override
    public List<OrderPageItemVO> getProductsInfo(List<OrderPageItemVO> orders) {
        List<OrderPageItemVO> list = new ArrayList<>();
        for(OrderPageItemVO order  :orders){
           OrderPageItemVO productsInfo = orderPageMapper.getProductsInfo(order.getPno());
           productsInfo.setItemCount(order.getItemCount());
           productsInfo.initSaleTotal();
           list.add(productsInfo);
        }
        return list;
    }
}
