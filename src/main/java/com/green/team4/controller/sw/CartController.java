package com.green.team4.controller.sw;

import com.green.team4.service.sw.CartService;
import com.green.team4.vo.sw.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/cart/*")
@RequiredArgsConstructor
public class CartController {

    // 의존성 주입
    private final CartService cartService;

    @GetMapping("/list")
    public void cartRead(int mno, Model model){ // 장바구니 List 가져오기
        log.info("CartController => cartRead(GET) 실행 => 받은 mno: "+mno);
        List<CartVO> cartList = cartService.readAll(mno);
        model.addAttribute("mno",mno);
        model.addAttribute("cartList",cartList);
    }

    @PostMapping("/delete")
    public String cartDelete(CartVO cartVO){ // 장바구니에서 상품 삭제
        log.info("CartController => cartDelete(POST) 실행 => 받은 CartVO: "+cartVO);
        cartService.remove(cartVO.getP_no());
        return "redirect:/sw/mypage/cart/read?mno="+cartVO.getMno();
    }
}
