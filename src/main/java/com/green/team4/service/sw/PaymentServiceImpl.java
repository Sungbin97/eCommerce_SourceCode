package com.green.team4.service.sw;

import com.green.team4.mapper.sw.PaymentMapper;
import com.green.team4.vo.sw.PaymentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    // 의존성 주입
    private final PaymentMapper paymentMapper;

    @Override
    public int register(PaymentVO paymentVO) { // 결제정보 신규 등록
        log.info("PaymentService => register 실행 => 받은 paymentVO: "+paymentVO);
        int result = paymentMapper.insert(paymentVO);
        log.info("PaymentService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override
    public List<PaymentVO> readAll(int mno) { // 결제정보 모두 가져오기(mno 단위)
        log.info("PaymentService => readAll 실행 => 받은 mno: "+mno);
        List<PaymentVO> result = paymentMapper.getAll(mno);
        log.info("PaymentService => readAll 실행 후 받은 결제정보들: "+result);
        return result;
    }

    @Override
    public PaymentVO readOne(int payINo) { // 결제정보 하나 가져오기(payINo 단위)
        log.info("PaymentService => readOne 실행 => 받은 payINo: "+payINo);
        PaymentVO result = paymentMapper.getOne(payINo);
        log.info("PaymentService => readOne 실행 후 받은 결제정보 하나: "+result);
        return result;
    }

    @Override
    public int modify(PaymentVO paymentVO) { // 결제정보 수정
        log.info("PaymentService => modify 실행 => 받은 paymentVO: "+paymentVO);
        int result = paymentMapper.update(paymentVO);
        log.info("PaymentService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int remove(int payINo) { // 결제정보 삭제
        log.info("PaymentService => remove 실행 => 받은 payINo: "+payINo);
        int result = paymentMapper.delete(payINo);
        log.info("PaymentService => remove 실행 후 삭제된 데이터 개수: "+result);
        return result;
    }
}
