package com.green.team4.service.sb;

import com.green.team4.mapper.sb.ProductMapper;
import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.vo.JH.Product_optVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductServiceOptImpl implements ProductOptService{
    private final ProductOptMapper productOptMapper;

    @Override
    public List<Product_optVO> getOpt1Names(int pno) {
        List list = new ArrayList();
        list.add(getOpt1Names(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getOpt2Names(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getColors(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getColors(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getColors(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getOpt(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getProductOption(pno));
        return list;
    }
}
