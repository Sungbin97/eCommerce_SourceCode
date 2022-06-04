package com.green.team4.mapper.dan;



import com.green.team4.vo.dan.SignupVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    public int insert(SignupVO vo);
}
