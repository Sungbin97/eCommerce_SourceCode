package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.ExchangeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExchangeMapper {

    int insert(ExchangeVO exchangeVO); // 취소/반품/교환 데이터 입력
    List<ExchangeVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    List<ExchangeVO> getAllAdmin(); // 데이터 전체 가져오기
    ExchangeVO getOne(int eno); // 데이터 하나 가져오기(eno로 검색)
    int update(ExchangeVO exchangeVO); // 데이터 수정
    int delete(int eno); // 데이터 삭제
}
