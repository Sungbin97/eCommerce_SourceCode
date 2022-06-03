package com.green.team4.controller.dan;

import com.green.team4.service.dan.SignupService;
import com.green.team4.vo.dan.SignupVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/dan*")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public void Signup(SignupVO vo) {
        System.out.println();
    }

    @PostMapping("/signup")
    public String SignupUpload(SignupVO signupVO){
        System.out.println("Signup PostMapping 작동"+ signupVO);
        signupService.insert(signupVO);
        return "redirect:/dan/samplemain";
    }
}
