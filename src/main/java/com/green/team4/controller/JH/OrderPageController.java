package com.green.team4.controller.JH;

import com.green.team4.service.JH.OrderPageService;
import com.green.team4.service.sb.MemberService;
import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class OrderPageController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderPageService orderPageService;

    @GetMapping("/order/{email}")
    public String orderPageGet(@PathVariable("email") String email, OrderPageVO orders, Model model){
        System.out.println("order입장");
        log.info("email : "+email);
        log.info("orders : "+orders.getOrders());

        model.addAttribute("orderlist",orderPageService.getProductListInfo(orders.getOrders()));
        model.addAttribute("memberInfo",memberService.getMemberInfo(email));
        return "/shop/orderSheet";
    }

    @PostMapping("/order")
    public String orderPagePost(DBOrderVO vo,Model model ){
        System.out.println("orderPagePost입장");
        log.info("DBorderVO : " + vo);
        orderPageService.order(vo);
        model.addAttribute("order",vo);
        return "/shop/orderCompleted";
    }
}
