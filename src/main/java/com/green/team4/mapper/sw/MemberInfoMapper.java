package com.green.team4.mapper.sw;

import com.green.team4.vo.sw.MemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberInfoMapper {

    int insert(MemberInfoVO memberInfoVO); // 데이터 입력(TestCode에서만 사용)
    MemberInfoVO getOne(int mno); // 데이터 하나 가져오기
    int update(MemberInfoVO memberInfoVO); // 데이터 수정
    int transfer(int mno); // 데이터 이동(탈퇴 회원 정보 이동(복사))
    int delete(int mno); // 데이터 삭제(탈퇴 회원 정보 삭제(memberInfo테이블에서 삭제))

}
