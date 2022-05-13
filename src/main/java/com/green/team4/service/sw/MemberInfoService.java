package com.green.team4.service.sw;

import com.green.team4.vo.sw.MemberInfoVO;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberInfoService {

    MemberInfoVO readOne(int mno);

    public List<MemberInfoVO> readAll();
    int modify(MemberInfoVO memberInfoVO);
    int remove(int mno,String delCategory, String delContent);
}
