package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.AdProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdProductMapper {
    int insert(AdProductVO vo);
    List<AdProductVO> getAll();
    AdProductVO getOne(int pno);
    int update(AdProductVO vo);
    int delete(int pno);
}
