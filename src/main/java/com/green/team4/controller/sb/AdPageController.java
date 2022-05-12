package com.green.team4.controller.sb;

import com.green.team4.vo.sb.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdPageController {
    @Autowired
    MailService mailService;
    @GetMapping("/sb/adpage")
    public void adPage(){
        System.out.println("adpgae");
    }

    @GetMapping("/sb/uploadEx")
    public void uploadEx(){

    }
}
