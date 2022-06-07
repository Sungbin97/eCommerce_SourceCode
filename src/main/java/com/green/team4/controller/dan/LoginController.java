package com.green.team4.controller.dan;

import com.green.team4.service.dan.LoginService;


import com.green.team4.vo.dan.LoginVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/dan")
public class LoginController {

    @Autowired
    private LoginService loginService;

//    @GetMapping("/login")
//    public void Login (LoginVO loginVO) {
//
//    }
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "errorMessage", required = false) String errorMessage,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        log.info("login error: "+error);
        log.info("login errorMessage: "+errorMessage);
        log.info("login exception: "+exception);
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("exception", exception);
        return "/dan/login";
    }

    @GetMapping("/denied")
    public void denied () {

    }
    @PostMapping("/logout")
    public String logout () {

        return "/dan/login";
    }


}

