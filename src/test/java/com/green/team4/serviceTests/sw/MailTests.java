package com.green.team4.serviceTests.sw;

import com.green.team4.vo.sb.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTests {
    @Autowired
    MailService mailService;

    @Test
    public void send(){
        mailService.sendMail();
    }
}
