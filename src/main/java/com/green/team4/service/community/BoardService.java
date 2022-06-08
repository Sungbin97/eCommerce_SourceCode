package com.green.team4.service.community;

import com.green.team4.vo.mypage.SearchVO;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public void insert(BoardVO vo);

    public BoardVO getOne(Long sid);

    public List<BoardVO> getList();
    public void modify(BoardVO vo);
    public void delete(Long sid);
    public List<BoardVO> getPageList(Criteria criteria);
    public int getTotal(Criteria criteria);
    public BoardVO userInfo(Long mno);
    public List<BoardVO> readListForMain();
    // SW 추가
    public List<BoardVO> readAllByMno(int mno);
    public List<BoardVO> readAllByMnoSearch(int mno, int pageNum, SearchVO searchVO);
}
