package com.green.team4.service.JH;

import com.green.team4.vo.JH.CategoryVO;

import java.util.List;

public interface CategoryService {
    public List<CategoryVO> cateList();

    /* 국내 카테고리 리스트 */
    public List<CategoryVO> getCateCode1();

    /* 외국 카테고리 리스트 */
    public List<CategoryVO> getCateCode2();
    public List<CategoryVO> getCateTier2();
    public List<CategoryVO> getCateTier3(String code);

}
