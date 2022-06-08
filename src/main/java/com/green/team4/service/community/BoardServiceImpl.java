package com.green.team4.service.community;

import com.green.team4.mapper.community.BoardMapper;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper mapper;


    @Override
    public void insert(BoardVO boardVO) {
        mapper.insert(boardVO);
        System.out.println("insert 서비스 작동");
    }

    @Override
    public BoardVO getOne(Long bno) {

        return mapper.readOne(bno);
    }

    @Override
    public List<BoardVO> getList() {

        return mapper.readList();
    }

    @Override
    public void modify(BoardVO boardVO) {
        mapper.modify(boardVO);
    }

    @Override
    public void delete(Long bno) {
        mapper.delete(bno);
    }

    @Override
    public List<BoardVO> getPageList(Criteria criteria) {
        criteria.setPage((criteria.getPage() - 1)* criteria.getPageNum());
        return mapper.getPageList(criteria);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return mapper.getTotalCount(criteria);
    }

    @Override
    public BoardVO userInfo(Long mno) {
        return mapper.userInfo(mno);
    }

    @Override
    public List<BoardVO> readListForMain() {
        return mapper.readListForMain();
    }
}
