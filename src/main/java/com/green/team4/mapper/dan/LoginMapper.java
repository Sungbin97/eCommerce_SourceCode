package com.green.team4.mapper.dan;



import com.green.team4.vo.dan.LoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public LoginVO login (LoginVO vo);

}
