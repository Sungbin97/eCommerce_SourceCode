package com.green.team4.service.sw;

import com.green.team4.mapper.sw.DeliveryMapper;
import com.green.team4.vo.sw.DeliveryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    // 의존성 주입
    private final DeliveryMapper deliveryMapper;


    @Override
    public DeliveryVO readOne(int ono) { // 배송조회 데이터 하나가져오기
        log.info("DeliveryService => readOne 실행 => 받은 ono: "+ono);
        DeliveryVO deliveryVO = deliveryMapper.getOne(ono);
        log.info("DeliveryService => readOne 실행 후 받은 deliveryVO: "+deliveryVO);
        return deliveryVO;
    }
}
