package com.green.team4.service.sb;

import com.green.team4.vo.JH.Product_optVO;

import java.util.List;

public interface ProductOptService {
    List<Product_optVO> getOpt1Names(int pno);
    List<Product_optVO> getOpt2Names(int pno);
    List<Product_optVO> getColors(int pno);
    List<Product_optVO> getOpt(int pno);
}
