package com.green.team4.service.sw;

import com.green.team4.vo.sw.PaymentVO;

import java.util.List;

public interface PaymentService {

    int register(PaymentVO paymentVO); // 결제정보 등록
    List<PaymentVO> readAll(int mno); // 결제정보 전체 가져오기(mno 단위)
    PaymentVO readOne(int payINo); // 결제정보 하나 가져오기
    int modify(PaymentVO paymentVO); // 결제정보 수정
    int remove(int payINo); // 결제정보 삭제
}
