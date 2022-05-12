package com.green.team4.mapperTests;

import com.green.team4.mapper.sb.AdProductMapper;
import com.green.team4.vo.sb.ProductVO;
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
        ProductVO productVO = new ProductVO();
        productVO.setPName("상품이름");
        productVO.setPColor("보라");
        productVO.setPSize("L");
        productVO.setPAmount(5);
        productVO.setPPrice(700000);
        productVO.setPInformation("남성용스포츠웨어");
        adProductMapper.insert(productVO);
    }

    @Test
    public void getAll(){
        List<ProductVO> result = adProductMapper.getAll();
        result.forEach(System.out::println);
    }

    @Test
    public void update(){
        ProductVO productVO = new ProductVO();
        productVO.setPno(3);
        productVO.setPInformation("바뀌나");
        adProductMapper.update(productVO);
    }

    @Test
    public void getOne(){
        System.out.println(adProductMapper.getOne(0));
    }
}
