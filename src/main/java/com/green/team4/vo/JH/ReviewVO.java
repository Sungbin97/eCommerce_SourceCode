package com.green.team4.vo.JH;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ReviewVO {
    private int rv_no;
    private int p_no ;

    private int rv_grade;
    private String rv_content;
    private String rv_writer ;
    private Date rv_regDate;
    private Date rv_updateDate;
}
