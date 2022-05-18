package com.green.team4.controller.sb;

import com.green.team4.service.sb.MailService;
import com.green.team4.service.sw.DeliveryService;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sb.MailVO;
import com.green.team4.vo.sw.DeliveryVO;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sb/order/*")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final ExchangeService exchangeService;
    private final MailService mailService;
    private final MemberInfoService memberInfoService;


    // Order -----------------------------------------------------------------------------------------

    @GetMapping("/list")
    public void orderList(Model model){ // 주문목록 모두 가져오기
        // 주문 목록 가져오기
        log.info("AdminOrderController => orderList(GET) 실행");
        List<OrderVO> orderList = orderService.readAllAdmin();
        System.out.println(orderList);
        model.addAttribute("orderList",orderList);
    }
    @PostMapping("/list")
    public String orderListPost(OrderVO vo){
        log.info("받아온 ono: "+vo.getOno());
        log.info("받아온 결제상태: "+vo.getPayStatus());
        log.info("받아온 상품명: "+vo.getPName());
        orderService.update(vo);
        int orderMno = orderService.readOne(vo.getOno()).getMno();
        System.out.println("ono를 통해 가져온 mno: "+orderMno);
        String memberMail = memberInfoService.readOne(orderMno).getEmail();
        String memberName = memberInfoService.readOne(orderMno).getName();
        String memberMessage = orderService.readOne(vo.getOno()).getMessage();
        MailVO mailVO = new MailVO();
        mailVO.setMno(orderMno);
        mailVO.setEmail(memberMail);
        
        if((vo.getPayStatus()).equals("결제완료") && (vo.getDeliveryStatus()).equals("배송준비중")){
            mailVO.setSubject("상품주문알림");
            mailVO.setText(memberName+"님의 주문이 완료되었습니다. 상품명: "+vo.getPName()
            +" 결제금액: "+vo.getTotalPrice());
            mailService.sendMail(mailVO);
        }
        if(vo.getPayStatus().equals("결제완료") && vo.getDeliveryStatus().equals("배송중")) {
            mailVO.setSubject("상품배송알림");
            mailVO.setText(memberName+"님의 상품이 배송중입니다.");
            mailService.sendMail(mailVO);
        }
        if(vo.getPayStatus().equals("결제완료") && vo.getDeliveryStatus().equals("배송완료")) {
            mailVO.setSubject("상품배송알림");
            mailVO.setText(memberName+"님의 상품배송이 완료되었습니다.");
            mailService.sendMail(mailVO);
        }
        return "redirect:/sb/order/list";
    }

    @GetMapping("/read")
    public void readOne(int ono, Model model){ // 주문 세부내용 가져오기
        log.info("OrderController => readOne(GET) 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderService.readOne(ono);
        model.addAttribute("orderVO",orderVO);
    }

    // Delivery -----------------------------------------------------------------------------------------

    @GetMapping("/delivery")
    public void readOneDelivery(int mno,int ono, Model model){ // 배송조회 화면 가져오기
        log.info("OrderController => readOneDelivery(GET) 실행 => 받은 ono: "+ono);
        log.info("OrderController => readOneDelivery(GET) 실행 => 받은 ono: "+mno);
        DeliveryVO deliveryVO = deliveryService.readOne(mno,ono);
        model.addAttribute("deliveryVO",deliveryVO);
    }

    // Exchange -----------------------------------------------------------------------------------------

    @GetMapping("/exchange")
    public void exRegister(int mno,int ono, int pno, int payINo, Model model){ // 취소/반품/교환 등록 페이지 가져오기
        log.info("OrderController => exRegister(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 ono: "+ono);
        log.info("OrderController => exRegister(GET) 실행 => 받은 pno: "+pno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 payINo: "+payINo);

        model.addAttribute("mno",mno);
        model.addAttribute("ono",ono);

        model.addAttribute("pno",pno);
        model.addAttribute("payINo",payINo);
    }

    @PostMapping("/exchange")
    public String exRegister(ExchangeVO exchangeVO){
        log.info("OrderController => exRegister(POST) 실행 => 받은 exchangeVO: "+exchangeVO);

        // 주문서 내 취소/반품/교환 신청 여부 내역 수정
        OrderVO orderVO = orderService.readOne(exchangeVO.getOno());
        orderVO.setExStatus(true);
        orderService.modify(orderVO);

        // 취소/반품/교환 신청 데이터 신규추가
        exchangeVO.setExStartDate(LocalDateTime.now());
        exchangeService.register(exchangeVO);
        return "redirect:/sw/mypage/order/list?mno="+exchangeVO.getMno();
    }



}
