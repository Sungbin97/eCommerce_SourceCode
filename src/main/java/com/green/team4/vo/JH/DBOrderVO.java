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
    //    mno int(30) not null, -- 회원번호
//    ono varchar(50) primary key , -- 주문번호
//    orderDate DATETIME default now(), -- 주문일자
//    receiverName varchar(30) , -- 받는 사람 성명
//    receiverPhone varchar(50) , -- 밥는 사람 연락처
//    postcode varchar(50), -- 우편번호
//    address varchar(50), -- 기본주소
//    detailAddress varchar(50) not null, -- 상세주소
//    message varchar(50) , -- 배송 메세지
//    tProductPrice int(30) , -- 총 상품가격(순수 상품가격)
//    tShipFee int(30) , -- 배송비
//    tUsePoint int(50), -- 총 사용한 포인트
//    tTotalPrice int(30) , -- 최총 총 결제금액(최종 결제금액 = 상품가+배송비-사용한포인트)
//    tPayStatus varchar(50) default '결제완료', -- 결제상태
//    tSavePoint int(50), -- 총 적립된 포인트
    private String ono; //주문번호
    private int mno;
    private int pno;
    private String receiverName; //배송받는사람
    private String receiverPhone ; //받는 사람 연락처
    
    private String email; //주문회원아이디
    private String id; // 회원 아이디
    private String phoneNum; // 주문자 번호
    private int count; //주문 갯수
    private String message;// 배송메세지

    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소


   
    private String orderStatus; // 주문상태


    private List<DBOrderItemVO> orders; //주문 상품


    private int tShipFee; // 배송비


    private int tUsePoint; //사용포인트

   
    private LocalDateTime orderDate; // 주문 날짜

    /* view에서 받아올 DB에 존재 하지 않는 데이터 */

    private int orderSalePrice; //판매가(모든 상품비용)
    private int tProductPrice ; //총 상품가격(순수 상품가격)
    private int tSavePoint; // 적립 포인트

    private int tTotalPrice; // 최종 판매 비용

    public void getOrderPriceInfo(){
        //상품비용 , 적립포인트 넣기
        for(DBOrderItemVO order : orders){
            tProductPrice += order.getTProductPrice();
            orderSalePrice += order.getITotalPrice();
            tSavePoint += order.getTotalSavePoint();
        }
        //배송비용 넣기
        if(orderSalePrice>=30000){
            tShipFee=0;
        }
        else{
            tShipFee=3000;
        }
        // 최종비용
        tTotalPrice = orderSalePrice + tShipFee - tUsePoint;
    }
}
