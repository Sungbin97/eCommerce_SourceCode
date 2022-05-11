package com.green.team4.vo.JH;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductVO {
    private int p_no;
    private  String p_name;
    private  String p_maincategory ;
    private  String p_subcategory ;
    private  String p_size;
    private int p_price;
    private int p_amount;
    private  String p_color;
    private Date p_regdate;
    private  String p_image;
    private  String p_information;
    private String p_delivery;
}
