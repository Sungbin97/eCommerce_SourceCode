package com.green.team4.vo.bs;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long uNo;
    private String userId;
    private String userName;
    private String userPassword;
    private String title;
    private String content;
    private Date regDate;
    private Date modDate;
    private boolean delete_state;
}
