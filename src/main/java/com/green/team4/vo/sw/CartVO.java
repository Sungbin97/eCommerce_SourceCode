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
    private int pno; // 상품 번호(상품 테이블과 foreign)

    // 상품 기본정보(장바구니 List 표시에 필요한 정보)
    private String pMainCategory; // 메인 카테고리
    private String pSubCategory; // 서브 카테고리
    private String pName; // 상품 이름
    private int pPrice; // 상품 가격
    private String pImage; // 상품 대표 이미지
}
