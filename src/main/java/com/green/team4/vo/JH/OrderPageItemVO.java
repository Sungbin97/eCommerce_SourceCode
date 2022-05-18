package com.green.team4.vo.JH;

import lombok.*;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
@ToString
public class OrderPageItemVO {

    /* 뷰로부터 전달받을 값 */
    private int pno; //상품번호

    private int itemCount; // 상품 수량
    private String pColor;
    /* DB로부터 꺼내올 값 */
    private String pName;

    private int pPrice;

    private double pDiscount; // 상품할인율
    /* 만들어 낼 값 */
    private int salePrice; //상품 파는가격(할인적용된 한개의 가격)

    private int totalPrice; // 총가격

    private int point; // 상품1개 구매후 받을 포인트

    private int totalPoint; // 총 받을 포인트

    public void initSaleTotal(){
        this.salePrice =(int) (this.pPrice * (1-this.pDiscount));
        this.totalPrice = this.salePrice * this.itemCount;
        this.point = (int)(Math.floor(this.salePrice*0.05));
        this.totalPoint = this.point*this.itemCount;
    }

}
