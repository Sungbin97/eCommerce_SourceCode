package com.green.team4.mapper.sb;

import com.green.team4.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insert(MemberVO memberVO);
    List<MemberVO> getAll();
    MemberVO getOne(int mno);
    int update(MemberVO memberVO);
    int delete(int mno);
}
