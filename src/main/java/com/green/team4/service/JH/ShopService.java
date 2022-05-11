package com.green.team4.service.JH;


import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.ProductVO;

import java.util.List;
import java.util.Map;

public interface ShopService {
    public List<ProductVO> getListAll();
    public List<ProductVO> getListWithCategory(String p_category);
    public int register(ProductVO pvo);
    public ProductVO getOne(int p_no);



    public List<ProductVO> getListByCategoryAndPage(Map<String,Object> map);


    public int getTotalProductCountbyCategory(String p_category);

    public List<ProductVO> getCategory(String p_category);


    public List<ProductVO> getListByFind(ItemPageCriteria cri);

    public int getTotaldatabyFind(ItemPageCriteria cri);

    public List<ProductVO> getListBySearch(String keyword);
}
