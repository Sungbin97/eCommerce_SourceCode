package com.green.team4.controller.sb;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sb/statistics/*")
@RequiredArgsConstructor
public class StatisticsController {

    @GetMapping("/sales")
    public void salesGet(){

    }
}
