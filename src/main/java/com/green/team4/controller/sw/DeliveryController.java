package com.green.team4.controller.sw;

import com.green.team4.mapper.sw.OrderItemMapper;
import com.green.team4.service.sw.CartService;
import com.green.team4.service.sw.DeliveryService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sw.CartVO;
import com.green.team4.vo.sw.DeliveryVO;
import com.green.team4.vo.sw.OrderItemVO;
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
@RequestMapping("/sw/mypage/delivery/*")
@RequiredArgsConstructor
public class DeliveryController {

    // 의존성 주입
    private final DeliveryService deliveryService;
    private final OrderItemMapper orderItemMapper;

    @GetMapping("/read")
    public void DeliveryRead(int oINo,String dCategory, Model model){ // 배송 정보 가져오기
        log.info("DeliveryController => DeliveryRead(GET) 실행 => 받은 oIno: "+oINo);
        log.info("DeliveryController => DeliveryRead(GET) 실행 => 받은 dCategory: "+dCategory);

        // 주문 상품 정보 가져오기
        OrderItemVO orderItemVO = orderItemMapper.getOne(oINo);
        model.addAttribute("item",orderItemVO);

        DeliveryVO deliveryVO = deliveryService.readOneByOINoDCat(oINo,dCategory);
        model.addAttribute("deliveryVO",deliveryVO);
    }
}
