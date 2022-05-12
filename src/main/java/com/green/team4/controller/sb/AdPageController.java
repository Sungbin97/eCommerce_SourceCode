package com.green.team4.controller.sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdPageController {
    @GetMapping("/sb/adpage")
    public void adPage(){
        System.out.println("adpgae");
    }

    @GetMapping("/sb/uploadEx")
    public void uploadEx(){

    }
}
