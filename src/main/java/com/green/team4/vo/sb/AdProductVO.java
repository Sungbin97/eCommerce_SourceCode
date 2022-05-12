package com.green.team4.vo.sb;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdProductVO {

    private int pno;
    private String pName;
    private String pCategory ;
    private String pSize;
    private int pPrice;
    private int pAmount;
    private String pColor;
    private Date pRegdate;
    private String pImage;
    private String pInformation;
    private String pDelivery;
    private String fileUrl;
}
