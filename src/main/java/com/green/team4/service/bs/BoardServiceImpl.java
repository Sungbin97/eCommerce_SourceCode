package com.green.team4.service.bs;

import com.green.team4.mapper.bs.BoardMapper;
import com.green.team4.vo.bs.BoardVO;
import com.green.team4.vo.bs.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper mapper;


    @Override
    public void insert(BoardVO vo) {
        mapper.insert(vo);
    }

    @Override
    public BoardVO getOne(Long sid) {

        return mapper.readOne(sid);
    }

    @Override
    public List<BoardVO> getList() {

        return mapper.readList();
    }

    @Override
    public void modify(BoardVO vo) {
        mapper.modify(vo);
    }

    @Override
    public void delete(Long sid) {
        mapper.delete(sid);
    }

    @Override
    public List<BoardVO> getPageList(Criteria criteria) {
        criteria.setPage((criteria.getPage() - 1)* criteria.getPageNum());
        return mapper.getPageList(criteria);
    }

    @Override
    public int getTotal(Criteria cri) {
        return mapper.getTotalCount(cri);
    }
}
