package com.green.team4.serviceTests.sw;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class methodTests {

    // 메서드 동작 확인용 Test
    @Test
    public void getNewOrderNumTest(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        System.out.println(dateStr);
    }
}
