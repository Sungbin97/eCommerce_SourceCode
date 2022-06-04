package com.green.team4;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.vo.sw.MemberInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class pagingTests {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

//    @Test
//    public void getMemberListByPaging(){
//        PageHelper.startPage(1,5);
//        Page<MemberInfoVO> memPage = memberInfoMapper.selectAll();
//        memPage.forEach(i-> System.out.println(i));
//        System.out.println("memPage: "+memPage);
//    }
}
