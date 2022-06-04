package com.green.team4.controller.sw;//package com.green.team4.controller.sw;

import com.github.pagehelper.PageInfo;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.service.sw.PersonalQService;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.OrderVO;
import com.green.team4.vo.sw.PersonalQVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/*")
@RequiredArgsConstructor
public class CSController {

    private final PersonalQService personalQService;


    @GetMapping("/personalQ/list") // 1:1문의글 목록 가져오기
    public void pQList(int mno,
                       @RequestParam(required = false, defaultValue = "1") int pageNum,
                       Model model){
        log.info("OrderController => pQList(Get) 실행 => 받은 mno: "+mno);
        log.info("OrderController => pQList(Get) 실행 => 받은 pageNum: "+pageNum);
        PageInfo<PersonalQVO> personalQList = new PageInfo<>(personalQService.readAllByMno(mno,pageNum),10);

        model.addAttribute("pqList",personalQList);
        model.addAttribute("mno",mno);
        model.addAttribute("pageNum",pageNum);
    }

    @GetMapping("/personalQ/read") // 1:1문의글 하나 가져오기
    public void pQRead(int pqNo, Model model){
        log.info("OrderController => pQRead(Get) 실행 => 받은 pqNo: "+pqNo);
        PersonalQVO personalQVO = personalQService.readOne(pqNo);

        model.addAttribute("pqVO",personalQVO);
    }

    @GetMapping("/personalQ/regQ") // 1:1문의글 등록페이지 가져오기
    public void pQRegister(int mno, Model model){
        log.info("OrderController => pQRegister(Get) 실행 => 받은 mno: "+mno);
        model.addAttribute("mno",mno);
    }

    @PostMapping("/personalQ/regQ") // 1:1문의글 등록 진행
    public String pQRegister(PersonalQVO personalQVO) {
        log.info("OrderController => pQRegister(POST) 실행 => 받은 personalQVO: "+personalQVO);
        personalQService.register(personalQVO); // DB 저장

        return "redirect:/sw/mypage/personalQ/list?mno="+personalQVO.getMno();
    }

    @PostMapping("/personalQ/delete") // 1:1문의글 접수 취소(삭제)
    public String pQDelete(int pqNo, int mno) {
        log.info("OrderController => pQDelete(POST) 실행 => 받은 pqNo: "+pqNo);
        log.info("OrderController => pQDelete(POST) 실행 => 받은 mno: "+mno);

        personalQService.remove(pqNo);

        return "redirect:/sw/mypage/personalQ/list?mno="+mno;
    }



}
