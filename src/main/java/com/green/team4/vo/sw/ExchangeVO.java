package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeVO {

    // tbl_exchange 에서 가져올 data
    private int mno; // 회원번호(tbl_memberInfo)
    private int ono; // 주문번호(tbl_order)
    private int eno; // 취소/반품/교환 번호
    private int pno; // 상품번호(tbl_product)
    private int payINo; // 결제수단 번호(tbl_paymentInfo)
    private String exCategory; // 분류(취소/반품/교환 중 택1)  -- 수정 가능
    private String exContent; // 사유 내용 -- 수정 가능
    private String exStatus; // 진행 상태(접수중, 접수완료, 처리완료)
    private LocalDateTime exStartDate; // 접수일
    private LocalDateTime exEndDate; // 완료일

    // tbl_product 에서 join 으로 가져올 data
    private String pName; // 상품 이름

    // tbl_order 에서 join 으로 가져올 data
    private int count; // 주문 개수
    private int productPrice; // 상품 총 금액
    private int shipFee; // 배송비
    private int totalPrice; // 총 결제금액

    // tbl_paymentInfo 에서 join 으로 가져올 data
    private String payName; // 결제 수단
    private String payContent; // 결제 수단 세부내용

    // 이미지 리스트
    private List<ExchangeFilesVO> exchangeFilesList;
}
