package com.green.team4.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestVO {

    // 멤버변수(테스트용 테이블 칼럼명과 일치)
    private int tno;
    private String name;
    private String content;
}
