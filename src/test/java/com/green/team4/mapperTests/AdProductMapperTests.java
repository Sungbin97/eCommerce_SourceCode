package com.green.team4.mapperTests;

import com.green.team4.mapper.sb.AdProductMapper;
import com.green.team4.vo.sb.AdProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdProductMapperTests {

    @Autowired
    AdProductMapper adProductMapper;

    @Test
    public void insert(){
        AdProductVO adProductVO = new AdProductVO();
        adProductVO.setPName("상품이름");
        adProductVO.setPColor("보라");
        adProductVO.setPCategory("옷");
        adProductVO.setPSize("L");
        adProductVO.setPAmount(5);
        adProductVO.setPPrice(700000);
        adProductVO.setPInformation("남성용스포츠웨어");
        adProductMapper.insert(adProductVO);
    }

    @Test
    public void getAll(){
        List<AdProductVO> result = adProductMapper.getAll();
        result.forEach(System.out::println);
    }

    @Test
    public void update(){
        AdProductVO adProductVO = new AdProductVO();
        adProductVO.setPno(3);
        adProductVO.setPInformation("바뀌나");
        adProductMapper.update(adProductVO);
    }

    @Test
    public void getOne(){
        System.out.println(adProductMapper.getOne(0));
    }
}
