package com.green.team4.vo.JH;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewLikeVO {
    private int likeno;
    private int pno;
    private int mno ;
    private int rno ;
    private int devCheck ;
    private Date devDate ;
}
