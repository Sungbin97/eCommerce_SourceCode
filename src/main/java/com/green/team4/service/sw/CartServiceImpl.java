package com.green.team4.service.sw;

import com.green.team4.mapper.sw.CartMapper;
import com.green.team4.vo.sw.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartMapper cartMapper;

    @Override
    public String register(CartVO cartVO) {
        log.info("CartService => register 실행 => 받은 cartVO: "+cartVO);
        // 중복 데이터 검토
        CartVO cart = cartMapper.getOneByPnoMno(cartVO.getPno(),cartVO.getMno());
        if(cart==null){
            int result = cartMapper.insert(cartVO);
            log.info("CartService => register 실행 후 등록된 데이터 개수: "+result);
            return "등록 완료되었습니다.";
        }
        else return "이미 장바구니에 추가되어있습니다.";
    }

    @Override
    public List<CartVO> readAll(int mno) { // 장바구니 모두 가져오기(mno단위)
        log.info("CartService => readAll 실행 => 받은 mno: "+mno);
        List<CartVO> result = cartMapper.getAll(mno);
        log.info("CartService => readAll 실행 후 받은 Cart List: "+result);
        return result;
    }

    @Override
    public int remove(int cno) { // 장바구니 삭제
        log.info("CartService => remove 실행 => 받은 cno: "+cno);
        int result = cartMapper.delete(cno);
        log.info("CartService => remove 실행 후 삭제된 개수: "+result);
        return result;
    }
}
