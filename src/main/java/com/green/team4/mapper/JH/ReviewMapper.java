package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {


    public List<ReviewVO> getReviewList(int p_no);

    public void write(ReviewVO rvvo);
    public int update(ReviewVO rvvo);

    public int delete(int rv_no);

}



