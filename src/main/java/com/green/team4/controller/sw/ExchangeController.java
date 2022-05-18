package com.green.team4.controller.sw;

import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/exchange/*")
@RequiredArgsConstructor
public class ExchangeController {

    // 의존성 주입
    private final ExchangeService exchangeService;
    private final OrderService orderService;

    @GetMapping("/list")
    public void getExList(int mno, Model model){ // 취소/반품/교환 List가져오기
        log.info("OrderController => getExList(Get) 실행 => 받은 mno: "+mno);
        List<ExchangeVO> exList = exchangeService.readAll(mno);
        model.addAttribute("mno",mno);
        model.addAttribute("exList",exList);
    }

    @GetMapping("/read")
    public void getExOne(int eno, Model model){ // 취소/반품/교환 하나 가져오기
        log.info("OrderController => getExOne(Get) 실행 => 받은 eno: "+eno);
        ExchangeVO exchangeVO = exchangeService.readOne(eno);
        model.addAttribute("exchangeVO",exchangeVO);
    }

    @PostMapping("/delete")
    public String exDelete(int eno, int mno, String ono){ // 취소/반품/교환 하나 가져오기
        log.info("OrderController => exDelete(POST) 실행 => 받은 eno: "+eno);
        log.info("OrderController => exDelete(POST) 실행 => 받은 eno: "+mno);

        // 취소/반품/교환 데이터 삭제
        exchangeService.remove(eno);

        // 주문서에 취소/반품/교환 신청상태 변경
        OrderVO orderVO = orderService.readOne(ono);
        orderVO.setExStatus(false);
        orderService.modify(orderVO);

        return "redirect:/sw/mypage/exchange/list?mno="+mno;
    }

}
