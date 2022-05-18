package com.green.team4.service.sw;

import com.green.team4.vo.sw.DeliveryVO;

public interface DeliveryService {

    DeliveryVO readOne(int mno, String ono); // 배송데이터 하나 가져오기
}
