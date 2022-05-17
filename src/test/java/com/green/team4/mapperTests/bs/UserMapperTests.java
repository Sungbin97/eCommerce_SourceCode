package com.green.team4.mapperTests.bs;

import com.green.team4.mapper.bs.UserMapper;
import com.green.team4.vo.bs.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    public UserMapper mapper;

//    @Test
//    public void readOneTest(){
//        System.out.println(mapper.readOne(1L));
//    }

    @Test
    public void insertTest(){
        for (int i = 0; i < 50; i++) {
            UserVO vo = new UserVO();
            vo.setUserId("testId777"+i);
            vo.setUserName("테스트길동 테길동"+i);
            vo.setContent("테스트내용 테내용" + i);
            vo.setTitle("테스트 제목 테제목"+i);
            vo.setUserPassword("1234"+i);
            mapper.insert(vo);
        }

    }

    @Test
    public void modifyTest(){
        UserVO vo = new UserVO();
        vo.setUserName("수정된 이름 수정이");
        vo.setTitle("수정된 제목 수정제");
        vo.setContent("수정된 내용 수정내");
        vo.setUNo(4L);
        mapper.modify(vo);
    }

    @Test
    public void readOneTest(){
        UserVO vo = mapper.readOne(4L);
        System.out.println("======================");
        System.out.println("@@ 결과 " + vo);
        System.out.println("======================");
    }

    @Test
    public void readListTest(){

        List<UserVO> list = mapper.readList();

        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

    }

}
