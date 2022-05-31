package com.green.team4.mapper.sb;

import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductOptMapper {
    int insert(Product_optVO vo);
    List<Product_optVO> getAll();
    int update(Product_optVO vo);
    int delete(int pno);
    List<Product_optVO> getColors(int pno);
    List<Product_optVO> getOpt(int pno);
}
