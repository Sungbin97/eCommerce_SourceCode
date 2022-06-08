package com.green.team4.controller.admin;

import com.green.team4.mapper.admin.StaticMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.vo.admin.StaticVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class AdminPageController {
    @Autowired
    MailService mailService;
    @Autowired
    private StaticMapper staticMapper;
    @GetMapping("/admin/adpage")
    public void adPage(Model model){
        log.info("AdminPageController => adPage(GET) 실행");
        int profit = staticMapper.getTotalProfit();
        int memberCnt = staticMapper.getTotalMemberCnt();
        int delMemCnt = staticMapper.getTotalDeleteMemCnt();
        int productCnt = staticMapper.getTotalProductCnt();
        log.info("staticVo profit 출력: "+profit);
        log.info("staticVo memberCnt 출력: "+memberCnt);
        log.info("staticVo delMemCnt 출력: "+delMemCnt);
        log.info("staticVo productCnt 출력: "+productCnt);

        model.addAttribute("profit",profit);
        model.addAttribute("memberCnt",memberCnt);
        model.addAttribute("delMemCnt",delMemCnt);
        model.addAttribute("productCnt",productCnt);
    }

}
