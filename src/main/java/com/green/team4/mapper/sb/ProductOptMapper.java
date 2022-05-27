package com.green.team4.mapper.sb;

import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductOptMapper {
    int insert(Product_optVO vo);
    List<Product_optVO> getAll();
    List<Product_optVO> getProductJoin(int pno);
    List<Product_optVO> getProductOption(int pno);
    Product_optVO getOpt(int pno);
    int updateAmt(Product_optVO vo); // 재고만 업데이트 (취소/반품/교환)
    int delete(int pno);
}
