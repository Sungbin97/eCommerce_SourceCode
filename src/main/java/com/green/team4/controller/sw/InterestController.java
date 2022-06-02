package com.green.team4.controller.sw;

import com.green.team4.service.sw.InterestService;
import com.green.team4.vo.sw.InterestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/interest/*")
@RequiredArgsConstructor
public class InterestController {

    // 의존성 주입
    private final InterestService interestService;

    @GetMapping("/list")
    public void itrRead(int mno, Model model){ // 찜 List 가져오기
        log.info("InterestController => itrRead(GET) 실행 => 받은 mno: "+mno);
        List<InterestVO> itrList = interestService.readAll(mno);
        model.addAttribute("itrList",itrList);
        model.addAttribute("mno",mno);
    }

    @PostMapping("/delete")
    public String itrDelete(InterestVO interestVO){ // 찜 삭제
        log.info("InterestController => itrDelete(POST) 실행 => 받은 interestVO: "+interestVO);
        interestService.remove(interestVO.getMno(),interestVO.getPno());
        return "redirect:/sw/mypage/interest/list?mno="+interestVO.getMno();
    }

    @PostMapping("/insert")
    public ResponseEntity<Integer> itrInsert(InterestVO interestVO ){
        log.info("위시리스트 insert : " + interestVO);
        int interestCheck=interestService.getOne(interestVO);
           if(interestCheck==0){ // 데이터가 없다면 등록
               interestService.register(interestVO);
               return new ResponseEntity<>(1, HttpStatus.OK);
           }
           else{ // 데이터가 있으면 삭제
               interestService.remove(interestVO.getMno(),interestVO.getPno());
               return new ResponseEntity<>(2, HttpStatus.OK);
           }

    }
    @GetMapping("/getOne")
    public ResponseEntity<Integer>  getOne(InterestVO interestVO){ // 찜 List 가져오기
        log.info("InterestController => getOne(GET) 실행 => 받은 interestVO: "+interestVO);
        int interestCheck=interestService.getOne(interestVO);
        return new ResponseEntity<>(interestCheck, HttpStatus.OK);
    }
}
