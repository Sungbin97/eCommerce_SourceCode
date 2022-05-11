package com.green.team4.mapperTests;

import com.green.team4.mapper.MemberMapper;
import com.green.team4.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class MemberMapperTests {
    @Autowired
    MemberMapper memberMapper;

    @Test
    public void testInsert(){
        for (int i = 0; i < 10; i++) {
            MemberVO memberVO = new MemberVO();
            memberVO.setName("최성빈"+i);
            memberVO.setAddr("용인"+i);
            memberVO.setResNum("111-777"+i);
            memberVO.setPhone("010-3105-534"+i);
            memberVO.setEmail(i+"fds@"+i+"few.com");
            memberMapper.insert(memberVO);
        }
    }
    @Test
    public void getAllTest(){
        List<MemberVO> result = memberMapper.getAll(); 
        result.forEach(System.out::println);
    }
    @Test
    public void testGet(){
        int mno = 5;
        MemberVO vo = memberMapper.getOne(mno);
        System.out.println(vo);
    }
    @Test
    public void testUpdate(){
        MemberVO vo = new MemberVO();
        MemberVO result = new MemberVO();
        vo.setMno(3);
        vo.setName("유양우");
        vo.setEmail("few@fff");
        vo.setPhone("013-321-32");
        vo.setAddr("성남");
        memberMapper.update(vo);
    }
    @Test
    public void testDelete(){
        MemberVO vo = new MemberVO();
        vo.setMno(5);
        memberMapper.delete(vo.getMno());
    }
}
