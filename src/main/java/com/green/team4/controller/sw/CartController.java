package com.green.team4.controller.sw;

import com.green.team4.service.sw.CartService;
import com.green.team4.vo.sw.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        int cartTotalPrice = 0;
        for(CartVO cart : cartList) cartTotalPrice += cart.getCTotalPrice();

        model.addAttribute("mno",mno);
        model.addAttribute("cartList",cartList); // 장바구니 List
        model.addAttribute("cartTotalPrice",cartTotalPrice); // 장바구니 물건 총 금액
    }

    @PostMapping("/modify") // 장바구니 업데이트 하기
    public ResponseEntity<CartVO> cartModify(@RequestBody CartVO cartVO){
        log.info("CartController => cartModify(POST) 실행 => 받은 cartVO: "+cartVO);

        // 장바구니 업데이트 서비스 호출
        CartVO updateCart = cartService.modify(cartVO); // 업데이트 후 수정된 장바구니 상품 가져오기
        log.info("업데이트된 장바구니 상품: "+updateCart);

        return new ResponseEntity<>(updateCart,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> cartRegister(@RequestBody CartVO cartVO){ // 장바구니 신규 추가
        log.info("CartController => cartRegister(POST) 실행 => 받은 cartVO: "+cartVO);
        String addResult = cartService.register(cartVO);
        return new ResponseEntity<>(addResult, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public String cartDelete(CartVO cartVO){ // 장바구니에서 상품 삭제
        log.info("CartController => cartDelete(POST) 실행 => 받은 CartVO: "+cartVO);
        cartService.remove(cartVO.getCno());
        return "redirect:/sw/mypage/cart/list?mno="+cartVO.getMno();
    }
}
