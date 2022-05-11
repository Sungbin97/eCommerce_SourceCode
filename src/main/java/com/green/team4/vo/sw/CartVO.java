package com.green.team4.vo.sw;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartVO {

    // 장바구니 기본정보
    private int mno; // 회원 번호(회원 테이블과 foreign)
    private int cno; // 장바구니 번호
    private int p_no; // 상품 번호(상품 테이블과 foreign)

    // 상품 기본정보(장바구니 List 표시에 필요한 정보)
    private String p_category;
    private String p_name;
    private int p_price;
    private String p_image;
}
