package com.green.team4.service.sb;

import com.green.team4.vo.ProductVO;

import java.util.List;

public interface ProductService {
    int pno(ProductVO vo);
    int insert(ProductVO vo);
    List<ProductVO> getAll();
    ProductVO getOne(int pno);
    int update(ProductVO vo);
    int delete(int pno);
}
