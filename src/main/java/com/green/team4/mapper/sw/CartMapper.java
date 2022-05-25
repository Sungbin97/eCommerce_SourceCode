package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int insert(CartVO cartVO); // 데이터 신규 추가
    CartVO getOneByPnoMno(int pno,int mno); // 장바구니 데이터 하나 가져오기(mno/pno 단위)
    List<CartVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    int delete(int cno); // 데이터 삭제
}
