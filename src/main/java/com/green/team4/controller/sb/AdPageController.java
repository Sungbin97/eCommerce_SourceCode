package com.green.team4.controller.sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdPageController {
    @GetMapping("/adpage")
    public void adPage(){
        System.out.println("adpgae");
    }

    @GetMapping("/uploadEx")
    public void uploadEx(){

    }
}
