package com.green.team4.mapper.bs;

import com.green.team4.vo.bs.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public int insert(UserVO vo);
    public int modify(UserVO vo);
    public UserVO readOne(Long uNo);
    public List<UserVO> readList();
    public void delete(Long uNo);
    public int selectUserTotalCount();
}
