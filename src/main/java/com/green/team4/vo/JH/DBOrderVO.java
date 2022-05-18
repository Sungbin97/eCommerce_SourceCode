package com.green.team4.vo.JH;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DBOrderVO {
    
    private String ono; //주문번호
    private int mno;
    private int pno;
    private String receiverName; //배송받는사람

    
    private String email; //주문회원아이디
    private String id;
//    private String addr;// 주소

    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소


   
    private String orderStatus; // 주문상태


    private List<DBOrderItemVO> orders; //주문 상품


    private int shipFee; // 배송비


    private int usePoint; //사용포인트

   
    private LocalDateTime orderDate; // 주문 날짜

    /* view에서 받아올 DB에 존재 하지 않는 데이터 */

    private int orderSalePrice; //판매가(모든 상품비용)

    private int orderSavePoint; // 적립 포인트

    private int orderFinalSalePrice; // 최종 판매 비용

    public void getOrderPriceInfo(){
        //상품비용 , 적립포인트 넣기
        for(DBOrderItemVO order : orders){
            orderSalePrice += order.getTotalPrice();
            orderSavePoint += order.getTotalSavePoint();
        }
        //배송비용 넣기
        if(orderSalePrice>=30000){
            shipFee=0;
        }
        else{
            shipFee=3000;
        }
        // 최종비용
        orderFinalSalePrice = orderSalePrice + shipFee - usePoint;
    }
}
