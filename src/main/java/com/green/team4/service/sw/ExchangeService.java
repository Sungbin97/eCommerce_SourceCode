package com.green.team4.service.sw;


import com.green.team4.vo.sw.ExchangeVO;

import java.util.List;

public interface ExchangeService {

    int register(ExchangeVO exchangeVO); // 취소/반품/교환 신청 등록
    List<ExchangeVO> readAll(int mno); // 취소/반품/교환 전체 가져오기(mno 단위)
    ExchangeVO readOne(int eno); // 취소/반품/교환 하나 가져오기
    int modify(ExchangeVO exchangeVO); // 취소/반품/교환 수정
    int remove(int eno); // 취소/반품/교환 삭제
}
