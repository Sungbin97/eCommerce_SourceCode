package com.green.team4.vo.sw;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShipmentVO {

    private int mno;
    private int sno;
    private String postcode;
    private String address;
    private String detailAddress;
}
