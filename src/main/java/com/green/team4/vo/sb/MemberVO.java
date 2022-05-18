package com.green.team4.vo.sb;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberVO {
    private int mno;
    private String name;
    private String resNum; //주민등록번호
    private String addr; //주소
    private String phone;
    private String email;

    //강정현 추가

    private int point;
    private String addr1; // 우편번호
    private String addr2; // 주소
    private String addr3; // 상세주소
}
