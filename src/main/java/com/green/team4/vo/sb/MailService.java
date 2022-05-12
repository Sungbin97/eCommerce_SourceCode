package com.green.team4.vo.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(){
        //수신 대상을 담을 ArrayList
        ArrayList<String> toUserList = new ArrayList<>();

        //수신 대상 추가
        toUserList.add("csb3694@naver.com");
        toUserList.add("swchung0521@gmail.com");

        //수신 대상 개수
        int toUserSize = toUserList.size();

        //SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 떄 이용)
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        //수신자 설정
        simpleMailMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        
        //메일 제목
        simpleMailMessage.setSubject("최성빈의 메일");

        //메일 내용
        simpleMailMessage.setText("ㅇㅈ바ㅣㅗㅓㄹㄷ지ㅗ릳죌ㄷㅈ");

        //메일 발송
        javaMailSender.send(simpleMailMessage);
    }
}
