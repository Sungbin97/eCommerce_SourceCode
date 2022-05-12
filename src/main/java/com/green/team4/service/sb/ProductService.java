package com.green.team4.service.sb;


import com.green.team4.vo.sb.AdProductVO;

import java.util.List;

public interface ProductService {
    int pno(AdProductVO vo);
    int insert(AdProductVO vo);
    List<AdProductVO> getAll();
    AdProductVO getOne(int pno);
    int update(AdProductVO vo);
    int delete(int pno);
}