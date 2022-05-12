package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insert(ProductVO vo);
    List<ProductVO> getAll();
    ProductVO getOne(int pno);
    int update(ProductVO vo);
    int delete(int pno);
}
