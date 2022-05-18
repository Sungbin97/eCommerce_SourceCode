package com.green.team4.serviceTests.sw;

import com.green.team4.service.sw.PaymentService;
import com.green.team4.vo.sw.PaymentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PayServiceTests {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testInsert(){
        String[] payName = {"휴대폰","신용카드","체크카드","카카오페이"};

        IntStream.rangeClosed(12,52).forEach(i->{ // 24는 테스트 중 삭제해서 건너띔
            PaymentVO paymentVO = new PaymentVO();
            paymentVO.setMno(i);
            paymentVO.setPayName(payName[(int)(Math.random()*payName.length)]);
            paymentVO.setPayContent("TestContent"+(i+1));
            paymentService.register(paymentVO);
        });
    }

    @Test
    public void testReadAll(){
        List<PaymentVO> result = paymentService.readAll(22);
        result.forEach(i-> System.out.println(i));
    }

    @Test
    public void testReadOne(){
        PaymentVO paymentVO = paymentService.readOne(29);
        System.out.println(paymentVO);
    }

    @Test
    public void testUpdate(){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMno(1);
        paymentVO.setPayINo(1);
        paymentVO.setPayName("PaymentName 수정");
        paymentVO.setPayContent("TestContent 수정");
        paymentService.modify(paymentVO);
    }

    @Test
    public void testDelete(){
        int result = paymentService.remove(49);
        System.out.println("삭제된 개수: "+result);
    }
}
