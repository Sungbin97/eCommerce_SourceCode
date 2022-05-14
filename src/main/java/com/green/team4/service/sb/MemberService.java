package com.green.team4.service.sb;

import com.green.team4.vo.sb.MemberVO;

import java.util.List;

public interface MemberService {
    int mno(MemberVO vo);
    int insert(MemberVO vo);
    List<MemberVO> getAll();
    MemberVO getOne(int mno);
    int update(MemberVO vo);
    int delete(int mno);

    //주문페이지에 사용할 멤버 서비스
    public MemberVO getMemberInfo(String email);

}
