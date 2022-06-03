package com.green.team4.service.dan;


import com.green.team4.vo.dan.LoginVO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public LoginVO login (LoginVO vo);
}
