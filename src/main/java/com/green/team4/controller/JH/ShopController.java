package com.green.team4.controller.JH;

import com.green.team4.service.JH.ShopService;
import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.PagingVO;
import com.green.team4.vo.sb.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/start")
    public void start(){

    }


    @GetMapping("/list")
    public void listGet( @ModelAttribute("cri") ItemPageCriteria cri, Model model){
        log.info("listget");

        PagingVO pagingVO = new PagingVO();
        System.out.println(cri);
        pagingVO.setCri(cri);
        pagingVO.setTotalProductData(shopService.getTotaldatabyFind(cri));
        model.addAttribute("list",shopService.getListByFind(cri));
        model.addAttribute("pagingVO",pagingVO);


    }


    // 상품 상세페이지
    @GetMapping("/read")
    public void read(int pno, Model model){
        log.info("read");
        log.info("p_no : "+pno);

        ProductVO pvo = shopService.getOne(pno);
        System.out.println(pvo);

        model.addAttribute("pvo",pvo);
        model.addAttribute("colors",shopService.getColors(pvo.getPCode()));
        model.addAttribute("sizes",shopService.getSizes(pvo.getPCode()));
        model.addAttribute("options",shopService.getOptList(pvo.getPCode()));


    }

    @GetMapping("/orderSheet")
    public void orderGet(int pno , Model model){
        log.info("orderSheet");
        log.info("p_no : "+pno);

        model.addAttribute("pvo",shopService.getOne(pno));

    }
    @GetMapping("/orderCompleted")
    public void orderCompleted(int p_no , Model model){


    }
}
