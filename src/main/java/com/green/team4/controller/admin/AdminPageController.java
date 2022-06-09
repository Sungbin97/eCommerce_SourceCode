package com.green.team4.controller.admin;

import com.green.team4.service.admin.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @Autowired
    MailService mailService;
    @GetMapping("/admin/adpage")
    public void adPage(){
        System.out.println("adpgae");
    }

}
