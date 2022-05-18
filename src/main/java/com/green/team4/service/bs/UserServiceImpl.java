package com.green.team4.service.bs;

import com.green.team4.mapper.bs.UserMapper;
import com.green.team4.vo.bs.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper mapper;


    @Override
    public void insert(UserVO vo) {
        mapper.insert(vo);
    }

    @Override
    public UserVO getOne(Long sid) {

        return mapper.readOne(sid);
    }

    @Override
    public List<UserVO> getList() {

        return mapper.readList();
    }

    @Override
    public void modify(UserVO vo) {
        mapper.modify(vo);
    }

    @Override
    public void delete(Long sid) {
        mapper.delete(sid);
    }
}
