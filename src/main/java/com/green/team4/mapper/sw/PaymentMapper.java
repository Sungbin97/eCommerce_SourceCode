package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    int insert(PaymentVO paymentVO); // 데이터 입력
    List<PaymentVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    PaymentVO getOne(int pno); // 데이터 하나 가져오기(pno로 검색)
    int update(PaymentVO paymentVO); // 데이터 수정
    int delete(int pno); // 데이터 삭제
}
