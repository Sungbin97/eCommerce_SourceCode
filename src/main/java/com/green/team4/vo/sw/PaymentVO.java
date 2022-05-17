package com.green.team4.vo.sw;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentVO {

    private int mno; // 회원번호
    private int payINo; // 결제수단 primary key
    private String payName; // 결제 대분류(휴대폰, 신용카드, 체크카드, 카카오페이 등)
    private String payContent; // 결제정보 세부내용(카드이름 등)
}
