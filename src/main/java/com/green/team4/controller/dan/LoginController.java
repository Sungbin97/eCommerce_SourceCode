package com.green.team4.controller.dan;

import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.dan.LoginService;


import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import com.green.team4.vo.dan.LoginVO;
import com.green.team4.vo.sw.MemberInfoVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
@RequestMapping("/dan")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MailService mailService;
    public static int generateAuthNumber() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }

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

    @GetMapping("/findPw")
    public void findPwGet(){

    }
    int authNum = generateAuthNumber();
    @PostMapping("/findPw")
    public ResponseEntity<Integer> findPwPost(@RequestBody MemberInfoVO vo) {

        MemberInfoVO findInfo = memberInfoMapper.findPw(vo);
        if(findInfo != null){
            MailVO mail = new MailVO();
            mail.setEmail(vo.getEmail());
            mail.setText("고객님의 비밀번호 찾기 인증번호가 발급되었습니다.");
            mail.setSubject("발급된 인증번호는 " + authNum + " 입니다.");
            mailService.sendMail(mail);
            System.out.println(mail.getSubject());
            int result = authNum;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return null;
    }
    @GetMapping("/newPw")
    public void newPwGet(){

    }
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/newPw")
    public String newPwPost(MemberInfoVO vo){
        vo.setId(vo.getId());
        log.info("받아온 id: " + vo.getId());
        String rowPw = vo.getPassword();
        log.info("받아온 비밀번호: "+rowPw);
        String encodePw = bCryptPasswordEncoder.encode(rowPw);
        log.info("encodePw: " + encodePw);
        vo.setPassword(encodePw);
        memberInfoMapper.newPw(vo);
        return "redirect:/dan/login";
    }

}

