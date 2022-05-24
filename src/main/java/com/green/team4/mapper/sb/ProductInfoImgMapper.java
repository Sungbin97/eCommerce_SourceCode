package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.ProductInfoImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoImgMapper {
    int insert(ProductInfoImgVO vo);
    List<ProductInfoImgVO> getAll();
    List<ProductInfoImgVO> getOneInfoList(int pno); //상품 이미지리스트 조회
    ProductInfoImgVO getOne(int pno);
    int update(ProductInfoImgVO vo);
    int delete(int pno);
    int deleteImg(int ino);
    ProductInfoImgVO getEvePno();
}
