package com.green.team4.controller.sb;

import com.green.team4.mapper.sb.MailMapper;
import com.green.team4.service.sb.MailService;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.sb.MailVO;
import com.green.team4.vo.sw.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sb/member/*")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberInfoService memberInfoService;
    private final MailMapper mailMapper;

    @GetMapping("/member")
    public void memberManage(Model model, int mno){
        model.addAttribute("getOne", memberInfoService.readOne(mno));
        model.addAttribute("list",memberInfoService.readAll());
    }

    @GetMapping("/modify")
    public void getModify(Model model, @RequestParam("mno") int mno){
        model.addAttribute("getOne", memberInfoService.readOne(mno));
    }

    @PostMapping("/modify")
    public String memberModify(MemberInfoVO memberInfoVO, Model model){
        log.info("수정"+memberInfoVO);
        model.addAttribute("memberVO", memberInfoVO);
        MailVO mvo = new MailVO();
        memberInfoService.modifyByAdmin(memberInfoVO);

        return "redirect:/sb/member/modify?mno="+memberInfoVO.getMno();
    }
    @PostMapping("/remove")
    public String MemberRemove(int mno,String delCategory, String delContent){
        memberInfoService.remove(mno,delCategory,delContent);
        log.info(mno+"번 회원 삭제");
        return "redirect:/sb/member/member?mno=1";
    }
}
