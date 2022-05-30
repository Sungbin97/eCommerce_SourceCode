package com.green.team4.service.JH;

import com.green.team4.mapper.JH.CategoryMapper;
import com.green.team4.vo.JH.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryVO> cateList() {
        return categoryMapper.cateList();
    }

    @Override
    public CategoryVO getCateName(String code) {
        return categoryMapper.getCateName( code);
    }

    @Override
    public List<CategoryVO> getCateCode1() {

        return categoryMapper.getCateCode1();
    }

    @Override
    public List<CategoryVO> getCateCode2() {

        return categoryMapper.getCateCode2();
    }

    @Override
    public List<CategoryVO> getCateTier2() {
        return categoryMapper.getCateTier2();
    }

    @Override
    public List<CategoryVO> getCateTier3WithCode(String code) {
        return categoryMapper.getCateTier3WithCode(code);
    }

    @Override
    public List<CategoryVO> getCateTier3() {
        return categoryMapper.getCateTier3();
    }
}
