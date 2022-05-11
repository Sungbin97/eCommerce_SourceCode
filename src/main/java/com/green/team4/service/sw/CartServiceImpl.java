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

    // 의존성 주입
    private final CartMapper cartMapper;


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
