package com.green.team4.mapperTests;

import com.green.team4.mapper.sb.ProductMapper;
import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.mapper.sb.ProductImgMapper;
import com.green.team4.service.sb.ProductOptService;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductImgVO;
import com.green.team4.vo.sb.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductOptMapper productOptMapper;
    @Autowired
    ProductImgMapper productImgMapper;
    @Autowired
    ProductOptService productOptService;

    public List<String> distinct(int pno, List list){
        List<Product_optVO> optList = productOptMapper.getOpt(pno);

        List<String> resultList = new ArrayList<>();

        optList.forEach(opt -> {
            resultList.add(opt.getPOptionName());
        });

        return resultList;
    }
    @Test
    public void getColors(){

        List<Product_optVO> optList = productOptMapper.getOpt(219);

        List<String> opt1NameList = new ArrayList<>();
        List<String> opt1List = new ArrayList<>();
        List<String> opt2NameList = new ArrayList<>();
        List<String> opt2List = new ArrayList<>();
        List<String> colorList = new ArrayList<>();

        optList.forEach(opt -> {
            opt1NameList.add(opt.getPOptionName());
            opt1List.add(opt.getPOption());
            opt2NameList.add(opt.getPOptionName2());
            opt2List.add(opt.getPOption2());
            colorList.add(opt.getPColor());
        });

        List<String> opt1NameListN = opt1NameList.stream().distinct().collect(Collectors.toList());
        List<String> opt1ListN = opt1List.stream().distinct().collect(Collectors.toList());
        List<String> opt2NameListN = opt2NameList.stream().distinct().collect(Collectors.toList());
        List<String> opt2ListN = opt2List.stream().distinct().collect(Collectors.toList());
        List<String> colorListN = colorList.stream().distinct().collect(Collectors.toList());

        opt1NameListN.forEach(System.out::println);
        opt1ListN.forEach(System.out::println);
        opt2NameListN.forEach(System.out::println);
        opt2ListN.forEach(System.out::println);
        colorListN.forEach(System.out::println);

    }

    @Test
    public void insert(){
        ProductVO productVO = new ProductVO();
        productVO.setPName("상품이름");
//        productVO.setPColor("보라");
//        productVO.setPSize("L");
        productVO.setPAmount(5);
        productVO.setPPrice(700000);
        productVO.setPInformation("여성용 스포츠웨어");
        productVO.setPMainCategory("의류");
        productVO.setPSubCategory("여성");
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
        System.out.println(productMapper.getOne(9));
    }

    @Test
    public void getEvePno(){
        System.out.println(productMapper.getEvePno());
    }

    //---------------옵션테스트---------------

    @Test
    public void optInset(){
        String [] sizes = {"90", "95", "100", "105", "110", "115"};
        String [] colors = {"빨강", "주황", "노랑", "초록", "파랑", "남색", "보라"};
        List<String> colorList = new ArrayList<String>();
        for (String i: colors) colorList.add(i);


        for (String c : colors){
            Product_optVO vo = new Product_optVO();
            vo.setPno(10);
            vo.setPAmount(10);
            vo.setPColor(c);
            for (String s : sizes){
                vo.setPOption2(s);
                productOptMapper.insert(vo);
            }
        }
    }

//    @Test
//    public void optUpdate(){
//        Product_optVO vo = productOptMapper.getOpt(1);
//        vo.setPColor("하기싫다");
//        vo.setPAmount(74);
//        productOptMapper.update(vo);
//    }

//    @Test
//    public void getOptJoin(){
//        System.out.println(productOptMapper.getProductJoin(10));
//    }
//
//    @Test
//    public void getOpt(){
//        System.out.println(productOptMapper.getOpt(1));
//    }

    @Test
    public void deleteOpt(){
        productOptMapper.delete(2);
    }

    @Test
    public void getOneImg(){
        List<ProductImgVO> result = productImgMapper.getOneImgList(16);
        result.forEach(System.out::println);
    }
    @Test
    public void getAll2(){
        List<ProductImgVO> result = productImgMapper.getAll();
        result.forEach(System.out::println);
    }



}
