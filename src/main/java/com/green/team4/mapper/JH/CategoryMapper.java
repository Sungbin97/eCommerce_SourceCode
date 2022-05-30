package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public List<CategoryVO> cateList();


    public List<CategoryVO> getCateCode1();

    public List<CategoryVO> getCateTier2();
    public List<CategoryVO> getCateTier3WithCode(String code);

    public List<CategoryVO> getCateTier3( );

    public List<CategoryVO> getCateCode2();
}
