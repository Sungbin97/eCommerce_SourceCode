package com.green.team4.controller.sb;

import com.green.team4.mapper.sw.OrderItemMapper;
import com.green.team4.service.sb.MailService;
import com.green.team4.service.sw.DeliveryService;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sw.DeliveryVO;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/sb/order/*")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final ExchangeService exchangeService;


    // 전체 종합 관리 --------------------------------------------------------------------------------
    @GetMapping("/list") // 전체 종합 관리 페이지
    public void orderList(Model model){ // 주문목록 모두 가져오기
        // 주문 상품 목록 가져오기
        log.info("AdminOrderController => orderList(GET) 실행");
        List<OrderItemVO> itemList = orderItemMapper.getAllAdmin();
        model.addAttribute("itemList",itemList);
    }


    // 일반 주문 관리 --------------------------------------------------------------------------------
    @GetMapping("/normalList") // 일반 주문 관리 페이지
    public void normalList(Model model){
        log.info("AdminOrderController => normalList(GET) 실행");

        // 일반 주문 가져오기(배송전/미신청 건만)
        List<OrderItemVO> normalList = orderItemMapper.getNormalDelivery();
        model.addAttribute("normalList",normalList);

    }

    // 취소 신청 관리 --------------------------------------------------------------------------------

    @GetMapping("/cancelList") // 취소 관리 페이지
    public void cancelList(Model model){
        log.info("AdminOrderController => cancelList(GET) 실행");

        // 취소/반품/교환 신청 목록 가져오기
        List<ExchangeVO> exchangeList = exchangeService.readAllAdmin();
        log.info("exchangeList: "+exchangeList);

        // 취소신청 건만 별도 저장
        List<ExchangeVO> cancelList = new ArrayList<>();
        exchangeList.forEach(exchangeVO -> {
            if(exchangeVO.getExCategory().equals("취소")){
                cancelList.add(exchangeVO);
            }
        });
        log.info("cancelList: "+cancelList);

        model.addAttribute("cancelList",cancelList);
    }

    @PostMapping("/cancelReg")
    public String cancelReg(String ono, int pno, int eno){
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 ono: "+ono);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 ono: "+pno);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 eno: "+eno);

        exchangeService.cancelAndReturn(ono,pno,eno);

        return "redirect:/sb/order/cancelList";
    }

    // 반품 신청 관리 --------------------------------------------------------------------------------
    @GetMapping("/returnList") // 반품 관리 페이지 => 반품 처리는 취소 처리 Service 동일하게 적용(cancelReg)
    public void returnList(Model model){
        log.info("AdminOrderController => returnList(GET) 실행");

        // 취소/반품/교환 신청 목록 가져오기
        List<ExchangeVO> exchangeList = exchangeService.readAllAdmin();
        log.info("exchangeList: "+exchangeList);

        // 반품신청 건만 별도 저장
        List<ExchangeVO> returnList = new ArrayList<>();
        exchangeList.forEach(exchangeVO -> {
            if(exchangeVO.getExCategory().equals("반품")){
                returnList.add(exchangeVO);
            }
        });
        log.info("returnList: "+returnList);

        model.addAttribute("returnList",returnList);
    }


    // 배송 처리 --------------------------------------------------------------------------------
    @GetMapping("/deliveryReg") // 배송 신규등록 화면
    public void deliveryRegister(String ono, int oINo, Model model){
        log.info("DeliveryController => deliveryRegister(GET) 실행 => 받은 oIno: "+oINo);
        log.info("DeliveryController => deliveryRegister(GET) 실행 => 받은 ono: "+ono);

        // 해당 주문 건의 mno 가져오기
        OrderVO orderVO = orderService.readOne(ono);
        int mno = orderVO.getMno();

        model.addAttribute("mno",mno);
        model.addAttribute("ono",ono);
        model.addAttribute("oINo",oINo);
    }

    @PostMapping("/deliveryReg") // 배송 신규등록 진행
    public String deliveryRegister(DeliveryVO deliveryVO){
        log.info("DeliveryController => deliveryRegister(POST) 실행 => 받은 deliveryVO: "+deliveryVO);

        // 배송 데이터 신규 등록
        deliveryService.register(deliveryVO);

        // 해당 주문상품 데이터 배송상태 변경
        OrderItemVO orderItemVO = orderItemMapper.getOne(deliveryVO.getOINo());
        orderItemVO.setIDeliveryStatus("배송준비중");
        orderItemMapper.update(orderItemVO);
        return "redirect:/sb/order/normalList";
    }

}
