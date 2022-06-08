package com.green.team4.controller.admin;

import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/mail/*")
@Log4j2
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final MailMapper mailMapper;
    private final MemberInfoService mem;

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String writePost(Model model, int mno, String subject, String text){
        MailVO vo = new MailVO();
        vo.setMno(mno);
        vo.setEmail(mem.readOne(mno).getEmail());
        vo.setSubject(subject);
        vo.setText(text);
        mailService.sendMail(vo);
        return "redirect:/admin/mail/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        log.info("MailController 실행 => list 실행");
        List<MailVO> mailList = mailMapper.getAll();
        log.info("MailController 실행 => list 실행 => 받은 mailList: "+mailList);
        model.addAttribute("list", mailList);
    }

}
