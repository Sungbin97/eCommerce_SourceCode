package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderVO {

    // tbl_order 칼럼
    private int mno; // 회원번호 (tbl_memberInfo)
    private String ono; // 주문번호
    private int pno; // 상품번호 (tbl_tbl_product)
    private int payINo; // 결제수단 번호 (tbl_paymentInfo)

    private String postcode; // 우편번호
    private String address; // 주소(도로명)
    private String detailAddress; // 상세주소
    private int usePoint; // 사용한 포인트

    private String receiverName; // 받는 사람 성명
    private String receiverPhone; // 받는 사람 연락처
    private String message; // 주문/배송 관련 메세지
    private int count; // 주문 개수
    private int productPrice; // 상품 총 금액
    private int shipFee; // 배송비
    private int totalPrice; // 총 결제금액
    private LocalDateTime orderDate; // 주문일시
    private String payStatus; // 결재 상태
    private String deliveryStatus; // 배송 상태
    private boolean exStatus; // 취소반품교환 신청 여부

    // tbl_memberInfo join 해서 가져올 칼럼
    private String name; // 주문자 성명 (tbl_memberInfo)
    private String phoneNum; // 주문자 연락처 (tbl_memberInfo)

    // tbl_product join 해서 가져올 칼럼
    private String pName;

    // tbl_paymentInfo join 해서 가져올 칼럼
    private String payName; // 결제 대분류(휴대폰, 신용카드, 체크카드, 카카오페이 등)
    private String payContent; // 결제정보 세부내용(카드이름 등)
}
