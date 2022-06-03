package com.green.team4.vo.bs;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
    private Long rNo;
    private Long uNo;
    private String reply;
    private String replyer;
    private Date replyDate;
    private Date modDate;
}