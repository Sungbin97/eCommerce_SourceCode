package com.green.team4.controller.sb;

import com.green.team4.service.MemberService;
import com.green.team4.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/*")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public void memberManage(Model model, int mno){
        model.addAttribute("getOne", memberService.getOne(mno));
        model.addAttribute("list",memberService.getAll());
    }

    @GetMapping("/modify")
    public void getModify(Model model, @RequestParam("mno") int mno){
        model.addAttribute("getOne", memberService.getOne(mno));
    }

    @PostMapping("/modify")
    public String memberModify(MemberVO memberVO, Model model){
        log.info("수정"+memberVO);
        model.addAttribute("memberVO", memberVO);
        memberService.update(memberVO);
        return "redirect:/member/modify?mno="+memberVO.getMno();
    }
    @PostMapping("/remove")
    public String MemberRemove(int mno){
        memberService.delete(mno);
        log.info(mno+"번 회원 삭제");
        return "redirect:/member/member?mno=1";
    }
}
