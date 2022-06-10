package com.green.team4.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
public class CommunityMaincontroller {
    @GetMapping("main")
    public void communityMain(){

    }
}
