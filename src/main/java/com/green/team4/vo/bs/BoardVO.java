package com.green.team4.vo.bs;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private Long bno;
    private Long mno;
    private String title;
    private String content;
    private Date regDate;
    private Date modDate;
    private Long totalCount; // count(*)
    private String bImg;
    private String nickName;
    private String nonMemberId;
    private String nonMemberPassword;
    private String community;
}
