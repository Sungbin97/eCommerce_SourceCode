package com.green.team4.controller.JH;

import com.green.team4.service.JH.ReviewService;
import com.green.team4.service.JH.ShopService;

import com.green.team4.vo.JH.ReviewVO;
import com.green.team4.vo.sb.ProductVO;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@Log4j2
@RequestMapping("/rest")
@RestController
public class RestjhController {

   @Autowired
   private  ReviewService reviewService;

    @Autowired
    private ShopService shopService;

    // review 불러오기
    @GetMapping("/getreviews/{p_no}")
    public ResponseEntity<List<ReviewVO>> getreviews(@PathVariable ("p_no") int p_no){
        log.info("getreviews 입장" );
        log.info(" p_no " + p_no);
        ResponseEntity<List<ReviewVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(reviewService.getReviewList(p_no), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }

    // 상품 상세 정보 불러오기(상세정보 , 배송정보)
    @GetMapping("/getinfo/{pno}")
    public ResponseEntity<ProductVO> getinfo(@PathVariable ("pno") int pno){
        log.info("getinfo 입장" );
        log.info(" p_no " + pno);
        ResponseEntity<ProductVO> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(shopService.getOne(pno) ,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }
    @GetMapping("/getListBySearch/{keyword}")
    public ResponseEntity<List<ProductVO>> getListBySearch(@PathVariable("keyword") String keyword){
        ResponseEntity<List<ProductVO>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(shopService.getListBySearch(keyword),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }


}
