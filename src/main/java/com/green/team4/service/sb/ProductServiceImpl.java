package com.green.team4.service.sb;

import com.green.team4.mapper.ProductMapper;
import com.green.team4.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;

    @Override
    public int pno(ProductVO vo) { return vo.getPno(); }

    @Override
    public int insert(ProductVO vo) {
        log.info("상품등록Service");
        return productMapper.insert(vo);
    }

    @Override
    public List<ProductVO> getAll() {
        return productMapper.getAll();
    }

    @Override
    public ProductVO getOne(int pno) {
        return productMapper.getOne(pno);
    }

    @Override
    public int update(ProductVO vo) {
        log.info("상품정보 수정 service");
        return productMapper.update(vo);
    }

    @Override
    public int delete(int pno) {
        log.info("상품 삭제");
        return productMapper.delete(pno);
    }
}
