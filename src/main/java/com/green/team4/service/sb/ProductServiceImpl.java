package com.green.team4.service.sb;

import com.green.team4.mapper.sb.AdProductMapper;
import com.green.team4.vo.sb.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

    private final AdProductMapper adProductMapper;

    @Override
    public int pno(ProductVO vo) { return vo.getPno(); }

    @Override
    public int insert(ProductVO vo) {
        log.info("상품등록Service");
        return adProductMapper.insert(vo);
    }

    @Override
    public List<ProductVO> getAll() {
        return adProductMapper.getAll();
    }

    @Override
    public ProductVO getOne(int pno) {
        return adProductMapper.getOne(pno);
    }

    @Override
    public int update(ProductVO vo) {
        log.info("상품정보 수정 service");
        return adProductMapper.update(vo);
    }

    @Override
    public int delete(int pno) {
        log.info("상품 삭제");
        return adProductMapper.delete(pno);
    }
}
