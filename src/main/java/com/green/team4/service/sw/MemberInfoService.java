package com.green.team4.service.sw;

import com.green.team4.vo.sw.MemberInfoVO;

import java.util.List;

public interface MemberInfoService {

    MemberInfoVO readOne(int mno);
    public List<MemberInfoVO> readAll(int pageNum);
    int modifyByMember(MemberInfoVO memberInfoVO);
    int modifyByAdmin(MemberInfoVO memberInfoVO);
    int remove(int mno,String delCategory, String delContent);
    public MemberInfoVO getMemberInfo(int mno); //주문페이지에 사용할 멤버 서비스

}
