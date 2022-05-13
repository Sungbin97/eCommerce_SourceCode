package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InterestVO {

    // 찜목록 기본정보
    private int mno; // 회원 번호(회원 테이블과 foreign)
    private int itrNo; // 장바구니 번호
    private int p_no; // 상품 번호(상품 테이블과 foreign)
    private LocalDateTime interestDate;

    // 상품 기본정보(tbl_product와 join해서 가져올 예정)
    private String p_category;
    private String p_name;
    private int p_price;
    private String p_image;
}
