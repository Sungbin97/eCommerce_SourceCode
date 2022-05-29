package com.green.team4.service.JH;


import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductVO;

import java.util.List;
import java.util.Map;

public interface ShopService {
    public List<ProductVO> getListAll();
    public List<ProductVO> getListWithCategory(String p_category);
    public int register(ProductVO pvo);
    public ProductVO getOne(int p_no);
    public ProductVO getOneWithPno (int p_no);
    //옵션을 통한 데이터 구하기
    public Product_optVO getProductWithOpt(Product_optVO povo);

    public List<ProductVO> getListByCategoryAndPage(Map<String,Object> map);


    public int getTotalProductCountbyCategory(String p_category);

    public List<ProductVO> getCategory(String p_category);


    public List<ProductVO> getListByFind(ItemPageCriteria cri);

    public int getTotaldatabyFind(ItemPageCriteria cri);

    public List<ProductVO> getListBySearch(String keyword);

    //    옵션
    public List<Product_optVO> getColors(int pno);
    public List<Product_optVO> getOptions(int pno);
    public List<Product_optVO> getOptions2(int pno);
    public List<Product_optVO> getOptList(Product_optVO povo);
    public List<ProductVO> getOneWithOpt(int pno);
    public Product_optVO getOptionPrice(Product_optVO povo);

}
