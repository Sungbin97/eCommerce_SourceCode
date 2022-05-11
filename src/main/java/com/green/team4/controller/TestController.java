package com.green.team4.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
public class TestController {

    @GetMapping("/test")
    public void testPage(){
        log.info("TestController => testPage(Get방식) 실행");
        log.info("test.html 이동");
    }
}
