package com.green.team4.vo.sb;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MailVO {
    private int mno;
    private String email;
    private int mailNo;
    private String subject;
    private String text;
    private LocalDateTime sendDate;
    private String reception;
}
