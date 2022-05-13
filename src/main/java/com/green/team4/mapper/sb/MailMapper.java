package com.green.team4.mapper.sb;

import com.green.team4.vo.sb.MailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {
    int insert(MailVO vo);
    List<MailVO> getAll();
    MailVO getOne(int mno);
    int update(MailVO vo);
    int delete(int mno);
}
