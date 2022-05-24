package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.ProductImgVO;
import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImgMapper {
    int insert(ProductImgVO vo);
    List<ProductImgVO> getAll(); //모든 상품의 모든 사진 조회
    List<ProductImgVO> getOneImgList(int pno); //한 상품의 모든 이미지 조회
    ProductImgVO getOne(int pno);
    int update(ProductImgVO vo);
    int delete(int pno);
    int deleteImg(int ino);
    ProductImgVO getEvePno();
}
