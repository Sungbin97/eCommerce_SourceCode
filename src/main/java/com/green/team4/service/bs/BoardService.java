package com.green.team4.service.bs;

import com.green.team4.vo.bs.BoardVO;
import com.green.team4.vo.bs.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void saveFile(BoardVO vo, MultipartFile imgFile) throws IOException;

    public List<BoardVO> readListForMain();
}
