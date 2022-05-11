package com.green.team4.vo.sw;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoVO {

    // 멤버변수
    private int mno; // 회원번호(변경 불가)
    private String id; // 아이디(변경 불가)
    private String password; // 비밀번호
    private String name; // 이름(변경 불가)
    private String nickName; // 별명
    private String email; // 이메일
    private String phoneNum; // 전화번호
    private String gender; // 성별(변경 불가)
    private String SSNum; // 주민등록번호(변경 불가)
    private String address; // 주소(거주지)
    private String auth; // 권한(관리자만 변경 가능)
    private String grade; // 등급(관리자만 변경 가능)
}
