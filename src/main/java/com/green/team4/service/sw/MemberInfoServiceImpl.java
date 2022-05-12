package com.green.team4.service.sw;

import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.mapper.sw.PaymentMapper;
import com.green.team4.mapper.sw.ShipmentMapper;
import com.green.team4.vo.sw.MemberInfoVO;
import com.green.team4.vo.sw.PaymentVO;
import com.green.team4.vo.sw.ShipmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService{

    // 의존성 주입
    private final MemberInfoMapper memberInfoMapper;
    private final ShipmentMapper shipmentMapper;
    private final PaymentMapper paymentMapper;

    // 회원정보 하나 가져오기
    @Override
    public MemberInfoVO readOne(int mno) {
        log.info("MemberInfoService => read 실행 => 받은 mno: "+mno);
        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(mno);
        log.info("memberInfoMapper 호출 => getOne 후 받은 memberInfoVO: "+memberInfoVO);
        return memberInfoVO;
    }

    // 회원정보 전체 가져오기
    @Override
    public List<MemberInfoVO> readAll() {
        log.info("MemberInfoService => readAll 실행");
        List<MemberInfoVO> memList = memberInfoMapper.getAll();
        memList.forEach(System.out::println);
        return memList;
    }

    // 회원정보 수정
    @Override
    public int modify(MemberInfoVO memberInfoVO) {
        log.info("MemberInfoService => modify 실행 => 받은 MemberInfoVO: "+memberInfoVO);
        int modResult = memberInfoMapper.update(memberInfoVO);
        log.info("memberInfoMapper 호출 => update 후 수정된 개수: "+modResult);
        return modResult;
    }

    // 회원정보 삭제(회원탈퇴) - 배송지 정보 및 결제 정보도 함께 삭제
    @Override
    public int remove(int mno) {
        log.info("MemberInfoService => remove 실행 => 받은 mno: "+mno);

        // 데이터 이동 ------------------------------------------------------------------------
        memberInfoMapper.transfer(mno); // 탈퇴 회원 정보 복사 => 회원탈퇴 테이블에 복사 붙여넣기

        // 데이터 삭제 ------------------------------------------------------------------------
        // 배송지 정보 삭제
        List<ShipmentVO> shipList = shipmentMapper.getAll(mno); // 탈퇴 회원 배송정보 모두 가져오기
        shipList.forEach(ship->shipmentMapper.delete(ship.getSno())); // 하나씩 꺼내서 배송지 삭제

        // 결제수단 정보 삭제
        List<PaymentVO> payList = paymentMapper.getAll(mno); // 탈퇴 회원 결제수단 정보 모두 가져오기
        payList.forEach(pay->paymentMapper.delete(pay.getPno())); // 하나씩 꺼내서 결제수단 삭제

        // 회원정보 삭제
        int delResult = memberInfoMapper.delete(mno);
        log.info("memberInfoMapper 호출 => remove 후 삭제된 개수: "+delResult);
        return delResult;
    }
}
