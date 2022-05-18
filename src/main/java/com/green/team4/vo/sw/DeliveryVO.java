package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryVO {

    private int mno; // 회원번호 (tbl_memberInfo)
    private String ono; // 주문번호 (tbl_order)
    private int dno; // 배송번호
    private String delCategory; // 배송 종류
    private String postcode; // 우편번호
    private String address; // 도로명 주소
    private String detailAddress; // 세부주소
    private String delCompany; // 택배사
    private String delStatus; // 배송 상태
    private String delLocation; // 배송 현재 위치
    private LocalDateTime delStartDate; // 배송 출발일시
    private LocalDateTime delEndDate; // 배송 종료일시
}
