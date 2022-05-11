package com.green.team4.controller.sw;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sw/mypage/*")
@Log4j2
public class MyPageController {

    @GetMapping("/main")
    public void getMainPage(int mno, Model model){
       log.info("MyPageController => getMainPage 실행 => 받은 mno: "+mno);
       model.addAttribute("mno",mno);
    }
}
