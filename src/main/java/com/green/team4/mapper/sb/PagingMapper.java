package com.green.team4.mapper.sb;

import com.github.pagehelper.Page;
import com.green.team4.paging.PagingEntity;
import com.green.team4.vo.sb.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PagingMapper {
    Page<PagingEntity> findProduct(SearchVO search);
}
