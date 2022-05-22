package com.green.team4.vo.JH;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DBOrderItemVO {
//    ono varchar(50), -- 주문 번호
//    oINo int(50) auto_increment primary key, -- 주문상품 번호
//    pno int(50), -- 상품 번호
//    iName varchar(50), -- 상품명
//    iOption varchar(50), -- 상품 옵션1
//    iOptionName varchar(50), -- 상품 옵션1 이름
//    iColor varchar(50), -- 상품 색상명
//    iPrice int(50), -- 상품 가격(순수 상품가격)
//    iDiscount int(30), -- 상품 할인률(%)
//    iDisPrice int(50), -- 할인된 상품 가격 (상품 가격에 할인률 적용한 가격)
//    iCount int(50), -- 상품 수량(주문한 상품 수량)
//    iTotalPrice int(50), -- 최종 결재금액
//    iDeliveryStatus varchar(30) default '배송전', -- 배송 상태
//    iExStatus varchar(30) default '미신청', -- 교환반품취소 신청 상태


    private String ono; //주문 번호
    private int pno; //상품 번호
    private String pName;
    private String pImageURL;
    private String pColor;
    private String pOption;
    private String pOptionName;
    private String pOption2;
    private String pOptionName2;
    private int iCount; // 주문수량

    private int orderItemno; // orderItem_tbl 기본키

    private int pPrice; // 상품 한 개 가격
    
    private double pDiscount; // 상품 할인율
    
    private int savePoint; // 상품 한개 구매 후 얻을 포인트(적립)



    // 뷰에서 받아올 데이터들 (dB에 없음)

    private int tProductPrice; // 총 순수 상품 가격(원가)(상품가격 * 수량)
    private int iDisPrice; // 할인 적용된 가격

    private int iTotalPrice; // 총 가격 (salePrice*ItemCount)

    private int totalSavePoint; // 총 획득 포인트(savePoint*ItemCount)

    public void initSaleTotal(){
        this.tProductPrice = this.pPrice * this.iCount;
        this.iDisPrice =(int) (this.pPrice * (1-pDiscount));
        this.iTotalPrice = this.iDisPrice*this.iCount;
        this.savePoint = (int) (Math.floor(this.pPrice*0.05));
        this.totalSavePoint = this.savePoint*this.iCount;
    }
}
