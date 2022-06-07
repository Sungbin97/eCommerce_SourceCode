package com.green.team4.controller.JH;

import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.service.JH.ShopService;
import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.PagingVO;
import com.green.team4.vo.sw.MemberInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private MemberInfoMapper memberInfoMapper;


    @GetMapping("/mainPage")
    public String  mainPage(@ModelAttribute("cri") ItemPageCriteria cri, Model model){
        log.info("메인페이지 입장");

        PagingVO pagingVO = new PagingVO();
        pagingVO.setCri(cri);
        pagingVO.setTotalProductData(shopService.getTotaldatabyFind(cri));
        model.addAttribute("list",shopService.getListByFind4(cri));
        model.addAttribute("pagingVO",pagingVO);
        return "/shop/mainPage";
    }

}
