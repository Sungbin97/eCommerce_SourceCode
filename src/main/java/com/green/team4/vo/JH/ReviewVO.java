package com.green.team4.vo.JH;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ReviewVO {
    private int rno;
    private int pno ;
    private int mno ;
    private int rRating;
    private int likeHit ;
    private String rImageURL ;
    private String rContent;


    private Date rRegdate;

    private Date rUpdatedate;


}
