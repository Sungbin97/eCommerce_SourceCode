package com.green.team4.service.sw;

import com.green.team4.mapper.sw.DeleteMemMapper;
import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.mapper.sw.PaymentMapper;
import com.green.team4.mapper.sw.ShipmentMapper;
import com.green.team4.vo.sw.DeleteMemVO;
import com.green.team4.vo.sw.MemberInfoVO;
import com.green.team4.vo.sw.PaymentVO;
import com.green.team4.vo.sw.ShipmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService{

    // 의존성 주입
    private final MemberInfoMapper memberInfoMapper;
    private final ShipmentMapper shipmentMapper;
    private final PaymentMapper paymentMapper;
    private final DeleteMemMapper deleteMemMapper;

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
    public int remove(int mno,String delCategory, String delContent) {
        log.info("MemberInfoService => remove 실행 => 받은 mno: "+mno);
        log.info("MemberInfoService => remove 실행 => 받은 delCategory: "+delCategory);
        log.info("MemberInfoService => remove 실행 => 받은 delContent: "+delContent);

        // 탈퇴회원 데이터 입력(tbl_deleteMem) -------------------------------------------------
        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(mno);

        // 기존 회원데이터 덮어쓰기
        DeleteMemVO deleteMemVO = new DeleteMemVO();
        deleteMemVO.setId(memberInfoVO.getId());
        deleteMemVO.setPassword(memberInfoVO.getPassword());
        deleteMemVO.setName(memberInfoVO.getName());
        deleteMemVO.setNickName(memberInfoVO.getNickName());
        deleteMemVO.setEmail(memberInfoVO.getEmail());
        deleteMemVO.setPhoneNum(memberInfoVO.getPhoneNum());
        deleteMemVO.setGender(memberInfoVO.getGender());
        deleteMemVO.setSSNum(memberInfoVO.getSSNum());
        deleteMemVO.setPostcode(memberInfoVO.getPostcode());
        deleteMemVO.setAddress(memberInfoVO.getAddress());
        deleteMemVO.setDetailAddress(memberInfoVO.getDetailAddress());
        deleteMemVO.setAuth(memberInfoVO.getAuth());
        deleteMemVO.setGrade(memberInfoVO.getGrade());

        // 회원탈퇴 사유 가져와서 set
        deleteMemVO.setDelCategory(delCategory);
        deleteMemVO.setDelContent(delContent);
        deleteMemVO.setDelDate(LocalDateTime.now());

        // DB 저장
        int delSaveCnt = deleteMemMapper.insert(deleteMemVO);

        log.info("MemberInfoService => remove 실행 => deleteMemMapper 실행 후 입력된 데이터 개수: "+delSaveCnt);

        // 데이터 삭제 ------------------------------------------------------------------------
        // 배송지 정보 삭제
        List<ShipmentVO> shipList = shipmentMapper.getAll(mno); // 탈퇴 회원 배송정보 모두 가져오기
        shipList.forEach(ship->shipmentMapper.delete(ship.getSno())); // 하나씩 꺼내서 배송지 삭제

        // 결제수단 정보 삭제
        List<PaymentVO> payList = paymentMapper.getAll(mno); // 탈퇴 회원 결제수단 정보 모두 가져오기
        payList.forEach(pay->paymentMapper.delete(pay.getPayINo())); // 하나씩 꺼내서 결제수단 삭제

        // 회원정보 삭제
        int delResult = memberInfoMapper.delete(mno);
        log.info("memberInfoMapper 호출 => remove 후 삭제된 개수: "+delResult);
        return delResult;




    }
    //주문 페이지 서비스
    @Override
    public MemberInfoVO getMemberInfo(String id) {
        return memberInfoMapper.getMemberInfo(id);
    }
}
