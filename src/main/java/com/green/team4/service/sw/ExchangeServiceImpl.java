package com.green.team4.service.sw;

import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.mapper.sw.ExchangeFilesMapper;
import com.green.team4.mapper.sw.ExchangeMapper;
import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sw.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Log4j2
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

    // 의존성 주입 (첨부파일 Mapper 여기서 한번에 작업)
    private final ExchangeMapper exchangeMapper;
    private final ExchangeFilesMapper exchangeFilesMapper;
    private final OrderService orderService;
    private final MemberInfoMapper memberInfoMapper;

    private final ProductOptMapper productOptMapper;



    // 내부 사용 메서드 -------------------------------------------------------------------------

    private String getNewOrderNum(){ // 주문날짜 신규생성

        // 날짜 문자열 생성
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        log.info("생성된 날짜 str: "+dateStr);

        // 난수 생성 (10자리)
        String randNum = "";
        for (int i = 0; i < 10; i++) {
            randNum += String.valueOf(Math.floor(Math.random()*8));
        }

        // 주문번호 조합 생성
        String orderNum = dateStr+randNum;
        log.info("새로 생성된 주문번호: "+orderNum);

        return orderNum;
    }

    private OrderVO getNewOrderVO(OrderVO oldVO, int pno){ // 신규 주문서 발행

        // (1) 취소 주문상품을 제외한 '업데이트 주문상품 List' 생성
        List<OrderItemVO> newItemlist = oldVO.getOrderItemList().stream().filter(i->i.getPno()!=pno).collect(Collectors.toList());
        log.info("신규 생성된 주문상품 list: "+newItemlist);


        // (2) 주문서 결제정보 업데이트에 활용할 정보 준비
        int tShipFee = oldVO.getTShipFee(); // 배송비
        int tUsePoint = oldVO.getTUsePoint(); // 사용한 포인트
        int tProductPrice = 0; // 상품 총 금액
        for(OrderItemVO item : newItemlist){
            tProductPrice += item.getITotalPrice();
        }
        int tTotalPrice = tProductPrice + tShipFee - tUsePoint; // 총 결제금액
//        int tSavePoint = (int) (tProductPrice*0.05); // 적립포인트 다시 산출
        int tSavePoint = 0;
        for(OrderItemVO item : newItemlist){
            tSavePoint += item.getISavePoint();
        }

        // (3) 주문서 신규 생성
        OrderVO newOrderVO = new OrderVO();
        // -- 주문서 기본정보 업데이트 --
        newOrderVO.setMno(oldVO.getMno());
        String newOno = getNewOrderNum(); // 주문번호 신규 생성
        newOrderVO.setOno(newOno);
        newOrderVO.setOrderDate(LocalDateTime.now()); // 주문일자 업데이트
        newOrderVO.setReceiverName(oldVO.getReceiverName());
        newOrderVO.setReceiverPhone(oldVO.getReceiverPhone());
        newOrderVO.setPostcode(oldVO.getPostcode());
        newOrderVO.setAddress(oldVO.getAddress());
        newOrderVO.setDetailAddress(oldVO.getDetailAddress());
        newOrderVO.setMessage(oldVO.getMessage());
        // -- 주문서 결제정보 업데이트 --
        newOrderVO.setTProductPrice(tProductPrice);
        newOrderVO.setTShipFee(tShipFee);
        newOrderVO.setTUsePoint(tUsePoint);
        newOrderVO.setTTotalPrice(tTotalPrice);
        newOrderVO.setTSavePoint(tSavePoint); // 적립 포인트
        // -- 주문상품 List 업데이트 --
        for(OrderItemVO nItem : newItemlist){
            nItem.setOno(newOno);
        }
        newOrderVO.setOrderItemList(newItemlist);

        return newOrderVO;
    }

    // Service -------------------------------------------------------------------------

    @Override
    public int register(ExchangeVO exchangeVO) { // 취소/반품/교환 신규 등록
        log.info("ExchangeService => register 실행 => 받은 exchangeVO: "+exchangeVO);

        // 신청서 등록
        int result = exchangeMapper.insert(exchangeVO);
        int key = exchangeVO.getEno();
        log.info("key: "+key);

        // 신청서 첨부파일 등록
        if(exchangeVO.getExchangeFilesList() != null){
            exchangeVO.getExchangeFilesList().forEach(file->{
                file.setEno(key); // 신청서 DB 등록된 PK 바로 가져와서 이미지 데이터에 저장
                log.info("등록 파일: "+file);
                exchangeFilesMapper.insert(file);
            });
        }
        return result;
    }

    @Override
    public List<ExchangeVO> readAll(int mno) { // 취소/반품/교환 내역 모두 가져오기(mno단위)
        log.info("ExchangeService => readAll 실행 => 받은 mno: "+mno);

        // 신청서 모두 가져오기
        List<ExchangeVO> exchangeList = exchangeMapper.getAll(mno);
        log.info("ExchangeService => readAll 실행 후 받은 exchangeList: "+exchangeList);

        // 신청서 첨부파일 모두 가져오기
        List<ExchangeVO> resultList = new ArrayList<>();

        exchangeList.forEach(exchangeVO -> {
            List<ExchangeFilesVO> fileList = exchangeFilesMapper.getAll(exchangeVO.getEno()); // 신청서 첨부파일 가져오기
            exchangeVO.setExchangeFilesList(fileList); // 신청서에 가져온 첨부파일 저장
            resultList.add(exchangeVO); // 신청서List에 저장
        });

        return resultList;
    }

    @Override
    public List<ExchangeVO> readAllAdmin() { // 취소/반품/교환 내역 모두 가져오기
        log.info("ExchangeService => readAllAdmin 실행");

        // 신청서 모두 가져오기
        List<ExchangeVO> exchangeList = exchangeMapper.getAllAdmin();
        log.info("ExchangeService => readAllAdmin 실행 후 받은 exList: "+exchangeList);

        // 신청서 첨부파일 모두 가져오기
        List<ExchangeVO> resultList = new ArrayList<>();

        exchangeList.forEach(exchangeVO -> {
            List<ExchangeFilesVO> fileList = exchangeFilesMapper.getAll(exchangeVO.getEno()); // 신청서 첨부파일 가져오기
            exchangeVO.setExchangeFilesList(fileList); // 신청서에 가져온 첨부파일 저장
            resultList.add(exchangeVO); // 신청서List에 저장
        });

        return resultList;
    }

    @Override
    public ExchangeVO readOne(int eno) { // 취소/반품/교환 내역 하나 가져오기

        // 신청서 하나 가져오기
        log.info("ExchangeService => readOne 실행 => 받은 eno: "+eno);
        ExchangeVO exchangeVO = exchangeMapper.getOne(eno);
        log.info("ExchangeService => readOne 실행 후 받은 exchangeVO: "+exchangeVO);

        // 신청서 첨부파일 가져오기
        List<ExchangeFilesVO> fileList = exchangeFilesMapper.getAll(eno);

        // 신청서에 가져온 첨부파일 저장
        exchangeVO.setExchangeFilesList(fileList);

        return exchangeVO;
    }

    @Override
    public int modify(ExchangeVO exchangeVO) { // 취소/반품/교환 내역 수정
        log.info("ExchangeService => modify 실행 => 받은 exchangeVO: "+exchangeVO);
        int result = exchangeMapper.update(exchangeVO);
        log.info("ExchangeService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int remove(int eno) { // 취소/반품/교환 내역 삭제
        log.info("ExchangeService => remove 실행 => 받은 eno: "+eno);

        // 신청서 첨부파일 삭제
        exchangeFilesMapper.delete(eno);

        // 신청서 삭제
        int result = exchangeMapper.delete(eno);
        log.info("ExchangeService => remove 실행 후 삭제된 데이터 개수: "+result);

        return result;
    }

    @Transactional
    @Override
    public void cancel(String ono, int pno, int eno) {
        log.info("ExchangeService => cancel 실행 => 받은 ono: "+ono);
        log.info("ExchangeService => cancel 실행 => 받은 pno: "+pno);

        // 해당 주문서 상태 변경 및 새로운 주문서 발행
        // (1) 기존 주문서 취소처리
        OrderVO orderVO = orderService.readOne(ono); // 해당 주문서 가져오기
        orderVO.setTPayStatus("주문취소완료");
        orderService.modifyStatus(orderVO);

        // (2) 해당 고객 기존 적립포인트 회수
        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(orderVO.getMno());
        int modPoint = memberInfoVO.getPoint() - orderVO.getTSavePoint(); // 회원 총 포인트 - 해당주문 적립 포인트
        memberInfoVO.setPoint(modPoint); // 회원 총 보유포인트 업데이트
        memberInfoMapper.updateByAdmin(memberInfoVO); // DB에 수정된 회원정보 저장

        // (3) 취소 상품 재고 현황 원상복구
        List<Product_optVO> optList = productOptMapper.getProductOption(pno); // 취소상품 옵션 모두가져오기
        List<OrderItemVO> cItemList = orderVO.getOrderItemList().stream().filter(i->i.getPno()==pno).collect(Collectors.toList()); // 취소 주문상품 가져오기
        log.info("optList: "+optList);
        log.info("cItemList: "+cItemList);
        List<Product_optVO> trgOptList = optList.stream().filter( // 취소 주문상품 옵션에 해당하는 옵션 테이블 데이터 필터
                opt->(opt.getPOption()).equals(cItemList.get(0).getIOption())
                && (opt.getPOption2()).equals(cItemList.get(0).getIOption2())
        ).collect(Collectors.toList());
        log.info("trgOptList: "+trgOptList);

        int cancelCnt = cItemList.get(0).getICount(); // 취소할 수량
        int originalCnt = trgOptList.get(0).getPAmount(); // 기존 재고

        trgOptList.get(0).setPAmount(originalCnt + cancelCnt); // 원복수량 = 기존 수량 + 취소 수량
        productOptMapper.updateAmt(trgOptList.get(0)); // DB에 재고 업데이트하여 저장


        if(orderVO.getOrderItemList().size()>1){ // 1주문에 2개 상품이상이 있는 건이면
            // (4) 새로운 주문서 발행 *취소 상품 제외한 나머지 상품 포함한 주문서로 재발행
            OrderVO newOrderVO = getNewOrderVO(orderVO,pno); // 신규 발행한 주문서
            orderService.register(newOrderVO); // DB에 새 주문서 등록

            // (5) 새로 산출한 적립포인트 회원정보에 업데이트
            MemberInfoVO newMemberInfoVO = memberInfoMapper.getOne(orderVO.getMno()); // 해당 회원정보 가져오기
            int oriPoint = newMemberInfoVO.getPoint(); // 기존 회원보유 포인트(취소에 따른 차감 반영 후)
            int newAddPoint = newOrderVO.getTSavePoint(); // 새로 다시 추가해야할 적립포인트
            int updatePoint = oriPoint + newAddPoint; // 새로 다시 적립후 최종 보유 포인트
            newMemberInfoVO.setPoint(updatePoint); // 해당 회원 포인트 현황 업데이트
            memberInfoMapper.updateByAdmin(newMemberInfoVO); // DB에 포인트 현황 업데이트한 회원정보 저장

            // (6) 취소 신청 테이블에서 해당 취소신청 건 진행상태 변경
            ExchangeVO exchangeVO = exchangeMapper.getOne(eno);
            exchangeVO.setExStatus("취소완료");
            exchangeVO.setExEndDate(LocalDateTime.now());
            exchangeMapper.update(exchangeVO);
        }
    }


}
