package com.green.team4.mapperTests;

import com.green.team4.mapper.ProductMapper;
import com.green.team4.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void insert(){
        ProductVO productVO = new ProductVO();
        productVO.setPName("상품이름");
        productVO.setPColor("보라");
        productVO.setPCategory("옷");
        productVO.setPSize("L");
        productVO.setPAmount(5);
        productVO.setPPrice(700000);
        productVO.setPInformation("남성용스포츠웨어");
        productMapper.insert(productVO);
    }

    @Test
    public void getAll(){
        List<ProductVO> result = productMapper.getAll();
        result.forEach(System.out::println);
    }

    @Test
    public void update(){
        ProductVO productVO = new ProductVO();
        productVO.setPno(3);
        productVO.setPInformation("바뀌나");
        productMapper.update(productVO);
    }

    @Test
    public void getOne(){
        System.out.println(productMapper.getOne(0));
    }
}
