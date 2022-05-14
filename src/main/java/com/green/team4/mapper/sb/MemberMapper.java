package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insert(MemberVO memberVO);
    List<MemberVO> getAll();
    MemberVO getOne(int mno);
    int update(MemberVO memberVO);
    int delete(int mno);

    //주문 페이지에서 사용 할 id 통한 회원구하기
    public MemberVO getMemberInfo(String email);
}
