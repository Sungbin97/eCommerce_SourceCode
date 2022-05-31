package com.green.team4.controller.sw;

import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.mapper.sw.OrderItemMapper;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.service.sw.ReviewMpService;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.OrderVO;
import com.green.team4.vo.sw.ReviewMpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/review/*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewMpService reviewMpService;

    @GetMapping("/list") // 내가 쓴 리뷰 글 List 가져오기
    public void reviewList(int mno, Model model){
        log.info("OrderController => reviewList(GET) 실행 => 받은 mno: "+mno);
        List<ReviewMpVO> reviewList = reviewMpService.readAllByMno(mno); // 접속 회원이 작성한 리뷰 모두 가져오기
        model.addAttribute("reviewList",reviewList);
        reviewList.forEach(i->{
            log.info(i);
        });
    }

    @GetMapping("/read")
    public void reviewRead(int rno,Model model){ // 리뷰 글 하나 가져오기
        log.info("OrderController => reviewRead(GET) 실행 => 받은 rno: "+rno);
        ReviewMpVO reviewMpVO = reviewMpService.readOneByRno(rno);
        model.addAttribute("review",reviewMpVO);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> reviewModify(@RequestBody ReviewMpVO reviewMpVO){ // 리뷰 수정 등록
        log.info("OrderController => reviewModify(POST) 실행 => 받은 reviewMpVO: "+reviewMpVO);
        // 리뷰 수정 진행
        reviewMpVO.setRUpdatedate(LocalDateTime.now()); // 수정일 반영
        int result = reviewMpService.modify(reviewMpVO); // 리뷰 수정 등록

        // 수정 결과 저장
        String reply = "";
        if(result == 1) reply = "리뷰 수정 완료하였습니다.";
        else reply = "리뷰 수정 실패하였습니다.";

        return new ResponseEntity<>(reply, HttpStatus.OK);
    }
}
