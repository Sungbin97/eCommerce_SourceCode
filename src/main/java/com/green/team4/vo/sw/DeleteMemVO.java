package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteMemVO {

    // 기존 MemberInfo 에서 가져오는 내용

    private String id; // 아이디(변경 불가)
    private String password; // 비밀번호
    private String name; // 이름(변경 불가)
    private String nickName; // 별명
    private String email; // 이메일
    private String phoneNum; // 전화번호
    private String gender; // 성별(변경 불가)
    private String SSNum; // 주민등록번호(변경 불가)
    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소
    private String auth; // 권한(관리자만 변경 가능)
    private String grade; // 등급(관리자만 변경 가능)

    // 삭제 회원 테이블 자체 신규 내용
    private LocalDateTime delDate; // 탈퇴 날짜
    private String delCategory; // 탈퇴 분류
    private String delContent; // 탈퇴 세부내용
}
