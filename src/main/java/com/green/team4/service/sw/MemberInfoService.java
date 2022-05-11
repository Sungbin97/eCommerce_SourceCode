package com.green.team4.service.sw;

import com.green.team4.vo.sw.MemberInfoVO;

public interface MemberInfoService {

    MemberInfoVO readOne(int mno);
    int modify(MemberInfoVO memberInfoVO);
    int remove(int mno);
}
