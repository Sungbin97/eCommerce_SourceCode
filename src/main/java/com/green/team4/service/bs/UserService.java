package com.green.team4.service.bs;

import com.green.team4.vo.bs.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void insert(UserVO vo);

    public UserVO getOne(Long sid);

    public List<UserVO> getList();
    public void modify(UserVO vo);
    public void delete(Long sid);
}
