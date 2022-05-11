package com.green.team4.serviceTests.sw;

import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sw.OrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void testReadAll(){
        int mno = 9;
        List<OrderVO> orderList = orderService.readAll(mno);
        orderList.forEach(i-> System.out.println(i));
    }

    @Test
    public void testReadOne(){
        int ono = 1;
        OrderVO orderVO = orderService.readOne(ono);
        System.out.println(orderVO);
    }
}
