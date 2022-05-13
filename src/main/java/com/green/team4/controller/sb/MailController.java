package com.green.team4.controller.sb;

import com.green.team4.mapper.sb.MailMapper;
import com.green.team4.service.sb.MailService;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.sb.MailVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sb/mail/*")
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
        return "redirect:/sb/mail/write";
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("list", mailMapper.getAll());
    }
}
