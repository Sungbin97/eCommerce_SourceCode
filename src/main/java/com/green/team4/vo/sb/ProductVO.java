package com.green.team4.vo.sb;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductVO {

    private int pno;
    private String pName;
    private String pMainCategory;
    private String pSubCategory;
    private double pRating;
    private int pReviewCnt;
    private int pPrice;
    private int pAmount;
    private Date pRegdate;
    private String pImage;
    private String pInformation;
    private String pDelivery;
    private String pImageURL;
    private String pCode;
    private String pSize;
    private String pColor;
}
