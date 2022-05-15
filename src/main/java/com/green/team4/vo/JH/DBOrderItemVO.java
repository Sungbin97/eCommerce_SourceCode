package com.green.team4.vo.JH;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DBOrderItemVO {

    private int ono; //주문 번호
    private int pno; //상품 번호
    private int itemCount; // 주문수량

    private int orderItemno; // orderItem_tbl 기본키
    
    private int pPrice; // 상품 한 개 가격
    
    private double pDiscount; // 상품 할인율
    
    private int savePoint; // 상품 한개 구매 후 얻을 포인트



    // 뷰에서 받아올 데이터들 (dB에 없음)


    private int salePrice; // 할인 적용된 가격

    private int totalPrice; // 총 가격 (salePrice*ItemCount)

    private int totalSavePoint; // 총 획득 포인트(savePoint*ItemCount)

    public void initSaleTotal(){
        this.salePrice =(int) (this.pPrice * (1-pDiscount));
        this.totalPrice = this.salePrice*this.itemCount;
        this.savePoint = (int) (Math.floor(this.pPrice*0.05));
        this.totalSavePoint = this.savePoint*this.itemCount;
    }
}
