package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<CartVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    int delete(int cno); // 데이터 삭제
}
