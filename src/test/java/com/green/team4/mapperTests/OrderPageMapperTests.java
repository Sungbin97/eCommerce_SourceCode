package com.green.team4.mapperTests;

import com.green.team4.mapper.JH.OrderPageMapper;
import com.green.team4.mapper.JH.ShopMapper;
import com.green.team4.vo.JH.DBOrderItemVO;
import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.Product_optVO;

import com.green.team4.vo.sb.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderPageMapperTests {
    @Autowired
    private OrderPageMapper orderPageMapper;
    private ShopMapper shopMapper;
    @Test
    public void getTest(){
        Product_optVO product_optVO = new Product_optVO();
        product_optVO.setPno(7);
        product_optVO.setPColor("red");
        product_optVO.setPOption("1.5KG");
        product_optVO.setPOption2("딸기맛");
        shopMapper.getProductWithOpt(product_optVO);

    }
}

//
//    @Test
////    public void getOrderInfoTest() {
////
////        DBOrderItemVO orderInfo = orderPageMapper.getOrderInfo(61);
////
////        System.out.println("result : " + orderInfo);
////    }
//
//
//    //@Test
//    public void enrollOrderTest() {
//
//        DBOrderVO ord = new DBOrderVO();
//        List<DBOrderItemVO> orders = new ArrayList();
//
//        DBOrderItemVO order1 = new DBOrderItemVO();
//
//        order1.setPno(61);
//        order1.setItemCount(5);
//        order1.setPPrice(70000);
//        order1.setPDiscount(0.1);
//        order1.initSaleTotal();
//
//
//
//        ord.setOrders(orders);
//
//        //ord.setOno(1);
//        ord.setMno(1);
//        ord.setReceiverName("test");
//        ord.setEmail("admin");
////        ord.setAddr1("test");
////        ord.setAddr2("test");
////        ord.setAddr3("test");
//        ord.setOrderStatus("배송중비");
//        ord.getOrderPriceInfo();
//        ord.setUsePoint(1000);
//
//        orderPageMapper.enrollOrder(ord);
//
//    }
////
////
//    @Test
//    public void enrollOrderItemTest() {
//
//        DBOrderItemVO oid = new DBOrderItemVO();
//
//       // oid.setOno(1);
//        oid.setPno(1);
//        oid.setItemCount(100);
//        oid.setPPrice(500);
//        oid.setPDiscount(0.01);
//
//        oid.initSaleTotal();
//
//        orderPageMapper.enrollOrderItem(oid);
//
//    }
////
////
////    @Test
////    public void deductMoneyTest() {
////
////        MemberVO member = new MemberVO();
////
////        member.setMno(1);
////        member.setMoney(500000);
////        member.setPoint(10000);
////
////        orderPageMapper.deductMoney(member);
////    }
////
//    /* 상품 재고 변경 */
//    @Test
//    public void deductStockTest() {
//        ProductVO productVO = new ProductVO();
//
//        productVO.setPno(1);
//        productVO.setPAmount(77);
//
//        orderPageMapper.deductStock(productVO);
//    }
////
////
////
////
////
////
////
////
////    /* 장바구니 제거(주문 처리) */
////    @Test
////    public void deleteOrderCart() {
////        String memberId = "admin";
////        int bookId = 3201;
////
////        CartDTO dto = new CartDTO();
////        dto.setMemberId(memberId);
////        dto.setBookId(bookId);
////
////        mapper.deleteOrderCart(dto);
////
////    }
//}
