package com.green.team4.service.sw;

import com.green.team4.vo.sw.MemberInfoVO;

import java.time.LocalDateTime;

public interface MemberInfoService {

    MemberInfoVO readOne(int mno);
    int modify(MemberInfoVO memberInfoVO);
    int remove(int mno,String delCategory, String delContent);
}
