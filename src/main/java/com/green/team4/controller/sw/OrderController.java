package com.green.team4.controller.sw;

import com.green.team4.mapper.sb.ProductMapper;
import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.mapper.sw.OrderItemMapper;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.service.sw.ReviewMpService;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.JH.ReviewVO;
import com.green.team4.vo.sb.ProductVO;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.OrderVO;
import com.green.team4.vo.sw.ReviewMpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/order/*")
@RequiredArgsConstructor
public class OrderController {

    // 의존성 주입
    private final OrderService orderService;
    private final ExchangeService exchangeService;
    private final ProductOptMapper productOptMapper;
    private final OrderItemMapper orderItemMapper;
    private final ReviewMpService reviewMpService;


    // Order -----------------------------------------------------------------------------------------

    @GetMapping("/list")
    public void orderList(int mno, Model model){ // 주문목록 모두 가져오기

        // 주문 목록 가져오기
        log.info("OrderController => orderList(GET) 실행 => 받은 mno: "+mno);
        List<OrderVO> orderList = orderService.readAll(mno);
        model.addAttribute("orderList",orderList);
        model.addAttribute("mno",mno);
    }

    @GetMapping("/read")
    public void readOne(String ono, Model model){ // 주문 세부내용 가져오기

        // 주문 내역서 가져오기
        log.info("OrderController => readOne(GET) 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderService.readOne(ono);
        log.info("OrderController => readOne(GET) 실행 후 받은 orderVO: "+orderVO);
        model.addAttribute("orderVO",orderVO);
    }

    // Review -----------------------------------------------------------------------------------------

    @GetMapping("/regReview") // 리뷰 신규 등록 페이지 가져오기
    public void regReview(int mno, int oINo, Model model){
        log.info("OrderController => regReview(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => regReview(GET) 실행 => 받은 oINo: "+oINo);

        // 리뷰 등록 대상 상품정보 가져오기
        OrderItemVO orderItemVO = orderItemMapper.getOne(oINo); // 주문상품 정보
        OrderVO orderVO = orderService.readOne(orderItemVO.getOno()); // 주문서
        LocalDateTime orderDate = orderVO.getOrderDate(); // 구매일자

        model.addAttribute("mno",mno);
        model.addAttribute("orderDate",orderDate);
        model.addAttribute("orderItemVO",orderItemVO);
    }

    @PostMapping("/regReview") // 리뷰 신규 등록 진행
    public String regReview(ReviewMpVO reviewMpVO){
        log.info("OrderController => regReview(POST) 실행 => 받은 reviewVO: "+reviewMpVO);
        reviewMpService.register(reviewMpVO);
        return "redirect:/sw/mypage/main?mno="+reviewMpVO.getMno();
    }

    // Exchange -----------------------------------------------------------------------------------------

    @GetMapping("/exchange")
    public void exRegister(int mno, String ono, int pno, Model model){ // 취소/반품/교환 등록 페이지 가져오기
        log.info("OrderController => exRegister(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 ono: "+ono);
        log.info("OrderController => exRegister(GET) 실행 => 받은 pno: "+pno);

        // (1) 기존 주문 정보 가져오기
        OrderVO orderVO = orderService.readOne(ono); // 주문서 가져오기
        List<OrderItemVO> itemList = orderVO.getOrderItemList(); // 주문 상품 List 가져오기
        itemList.forEach(i->{ // 신청 대상 아이템 model 등록
            if(i.getPno()==pno) model.addAttribute("orderItem",i);
        });
        model.addAttribute("mno",mno);

        // (2) 변경 주문 옵션 display용 데이터 가져오기
        List<Product_optVO> optList = productOptMapper.getProductOption(pno); // 해당 상품 옵션정보 가져오기

        // (3) 해당 상품 각 주문옵션 데이터 담을 List
        List<String> opt1List = new ArrayList<>();
        List<String> opt2List = new ArrayList<>();
        List<String> colorList = new ArrayList<>();

        // (4) 가져온 주문 옵션 List에서 하나씩 꺼내서 옵션별로 해당 List에 담기
        optList.forEach(opt->{
            opt1List.add(opt.getPOption());
            opt2List.add(opt.getPOption2());
            colorList.add(opt.getPColor());
        });

        // (5) (4)순서에서 배분한 각 List에 중복 값 제거
        List<String> opt1ListN = opt1List.stream().distinct().collect(Collectors.toList());
        List<String> opt2ListN = opt2List.stream().distinct().collect(Collectors.toList());
        List<String> colorListN = colorList.stream().distinct().collect(Collectors.toList());

        opt1ListN.forEach(i->log.info(i));
        opt2ListN.forEach(i->log.info(i));
        colorListN.forEach(i->log.info(i));

        model.addAttribute("itemOptionList",optList);
        model.addAttribute("opt1ListN",opt1ListN);
        model.addAttribute("opt2ListN",opt2ListN);
        model.addAttribute("colorListN",colorListN);

    }

    @PostMapping("/exchange")
    public String exRegister(ExchangeVO exchangeVO){
        log.info("OrderController => exRegister(POST) 실행 => 받은 exchangeVO: "+exchangeVO);

        // 주문 상품 테이블 내 취소/반품/교환 신청 여부 내역 수정
        OrderVO orderVO = orderService.readOne(exchangeVO.getOno()); // 주문서 가져오기
        List<OrderItemVO> itemList = orderVO.getOrderItemList(); // 주문서 내 주문 상품 List 가져오기
        itemList.forEach(i->{
            if(i.getPno()==exchangeVO.getPno()){ // 취소/반품/교환 신청 대상 item 찾아서
                i.setIExStatus("신청완료"); // 신청 완료 상태로 업데이트
                orderService.modifyItem(i); // 주문서 내 주문상품 수정
            }
        });
        // 취소/반품/교환 신청 데이터 신규추가
        exchangeVO.setExDate(LocalDateTime.now());
        exchangeService.register(exchangeVO);

        return "redirect:/sw/mypage/order/read?ono="+exchangeVO.getOno();
    }



}
