package com.green.team4.controller.dan;

import com.green.team4.service.dan.LoginService;


import com.green.team4.vo.dan.LoginVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/dan")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public void Login (LoginVO loginVO) {

    }
    @GetMapping("/denied")
    public void denied () {

    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginPost(@RequestBody LoginVO vo){
//        System.out.println("로그인 Post Mapping 작동하나 "  +vo);
//        LoginVO login = loginService.login(vo);
//        String success = "";
////        String fail = "";
//        if (login != null) success ="성공";
//        else success ="실패";
////        fail= "아이디 또는 비밀번호가 맞지 않습니다.";
//        System.out.println("컨트롤러에서 success 값 확인"+ success);
//
//        return new ResponseEntity<>(success, HttpStatus.OK);
//    }
}

