package com.green.team4.mapper;

import com.green.team4.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    public int insert(TestVO testVO); // 데이터 등록
    public List<TestVO> getAll(); // 전체 데이터 가져오기
    public TestVO getOne(int tno); // 데이터 하나 가져오기
    public int update(TestVO testVO); // 데이터 수정
    public int delete(int tno); // 데이터 삭제
}
