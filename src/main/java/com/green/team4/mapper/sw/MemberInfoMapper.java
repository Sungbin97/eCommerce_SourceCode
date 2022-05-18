package com.green.team4.mapper.sw;

import com.green.team4.vo.sb.MemberVO;
import com.green.team4.vo.sw.MemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberInfoMapper {

    int insert(MemberInfoVO memberInfoVO); // 데이터 입력(TestCode에서만 사용)
    List<MemberInfoVO> getAll(); // 데이터 전체 가져오기
    MemberInfoVO getOne(int mno); // 데이터 하나 가져오기
    int update(MemberInfoVO memberInfoVO); // 데이터 수정
    int delete(int mno); // 데이터 삭제(탈퇴 회원 정보 삭제(memberInfo테이블에서 삭제))


    //주문 페이지에서 사용 할 id 통한 회원구하기
    public MemberInfoVO getMemberInfo(String id);
}
