package com.green.team4.vo.JH;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product_optVO {
    private int opt_no ;
    private String opt_id ;
    private int opt_type ;
    private String p_code ;
    private int opt_price ;
    private int opt_stock_qty ;
    private int opt_noti_qty ;
}
