package com.green.team4.mapperTests;

import com.green.team4.mapper.JH.ReviewMapper;
import com.green.team4.vo.JH.ReviewVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper mapper;

    @Test
    public void testGetList(){

        
    }
    @Test
    public void testGetList2(){

        
    }

    @Test
    public void testInsert(){
        String[] strarr = {"의류","식품","장비"};

        for (int i = 0; i < 200; i++) {
            ReviewVO rvo = new ReviewVO();
            rvo.setP_no((int)(Math.random()*200)+1);
            rvo.setRv_content("테스트내용");
            rvo.setRv_writer("테스트 작성자");
            mapper.write(rvo);
        }
    }
    @Test
    public void testGetone(){
      


    }
}
