package com.green.team4.mapperTests;

import com.green.team4.mapper.TestMapper;
import com.green.team4.vo.TestVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperFirstTests {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void insertTest(){
        for (int i = 0; i < 20; i++) {
            TestVO testVO = new TestVO();
            testVO.setName("이름"+i);
            testVO.setContent("내용"+i);
            testMapper.insert(testVO); // mapper 호출
        }
    }

    @Test
    public void getAllTest(){
        List<TestVO> result = testMapper.getAll(); // mapper 호출
        result.forEach(i-> System.out.println(i));
    }

    @Test
    public void getOneTest(){
        int tno = 9;
        TestVO result = testMapper.getOne(tno); // mapper 호출
        System.out.println(result);
    }

    @Test
    public void updateTest(){
        TestVO testVO = new TestVO();
        testVO.setTno(8);
        testVO.setName("이름 수정");
        testVO.setContent("내용 수정");
        testMapper.update(testVO); // mapper 호출
    }
    
    @Test
    public void deleteTest(){
        int tno = 5;
        testMapper.delete(tno); // mapper 호출
    }
}
