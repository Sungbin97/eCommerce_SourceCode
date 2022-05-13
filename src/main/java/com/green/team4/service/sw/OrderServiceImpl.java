package com.green.team4.service.sw;

import com.green.team4.mapper.sw.OrderMapper;
import com.green.team4.vo.sw.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // 의존성 주입
    private final OrderMapper orderMapper;


    @Override
    public List<OrderVO> readAll(int mno) { // 주문목록 모두 가져오기(mno 단위)
        log.info("OrderService => readAll 실행 => 받은 mno: "+mno);
        List<OrderVO> orderList = orderMapper.getAll(mno);
        log.info("OrderService => readAll 실행 후 받은 orderList: "+orderList);
        return orderList;
    }

    @Override
    public OrderVO readOne(int ono) { // 주문내역 하나 가져오기
        log.info("OrderService => readOne 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderMapper.getOne(ono);
        log.info("OrderService => readOne 실행 후 받은 orderVO: "+orderVO);
        return orderVO;
    }

    @Override
    public int modify(OrderVO orderVO) { // 주문내역 수정(결재상태,배송상태만 수정) - 관리자 전용
        log.info("OrderService => modify 실행 => 받은 orderVO: "+orderVO);
        int result = orderMapper.update(orderVO);
        log.info("OrderService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }
}
