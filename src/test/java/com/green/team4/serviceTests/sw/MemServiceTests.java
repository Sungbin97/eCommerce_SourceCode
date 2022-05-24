package com.green.team4.serviceTests.sw;

import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.sw.MemberInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemServiceTests {

    @Autowired
    private MemberInfoService service;

    @Autowired
    private MemberInfoMapper mapper;

    @Test
    public void testInsert(){ // Test데이터 입력

        String[] gender = {"남성","여성"};
        String[] auth = {"일반","에디터","관리자"};
        String[] grade = {"일반","우수","최우수"};
        String[] itrSports = {"농구","축구","배구"};

        IntStream.rangeClosed(1,50).forEach(i->{
            MemberInfoVO memberInfoVO = new MemberInfoVO();
            memberInfoVO.setId("Id"+i);
            memberInfoVO.setPassword("pw"+i);
            memberInfoVO.setName("name"+i);
            memberInfoVO.setNickName("nickName"+i);
            memberInfoVO.setGender(gender[(int)(Math.random()*2)]);
            memberInfoVO.setSSNum("99999-"+i);
            memberInfoVO.setEmail("email"+i+"@test.com");
            memberInfoVO.setPhoneNum("031-"+i);
            memberInfoVO.setMobileNum("010-"+i);
            memberInfoVO.setPostcode(""+(int)(Math.random()*(10000-9999)+9999));
            memberInfoVO.setAddress("기본주소"+i);
            memberInfoVO.setDetailAddress("세부주소"+i);
            memberInfoVO.setInterestSports(itrSports[(int)(Math.random()*3)]);
            memberInfoVO.setAuth(auth[(int)(Math.random()*3)]);
            memberInfoVO.setGrade(grade[(int)(Math.random()*3)]);
            mapper.insert(memberInfoVO);
        });
    }
    @Test
    public void testReadOne(){
        int mno = 20;
        MemberInfoVO memberInfoVO = service.readOne(mno);
        System.out.println("가져온 MemberInfo: "+memberInfoVO);
    }

    @Test
    public void testGetAll(){
        List<MemberInfoVO> list = service.readAll();
        list.forEach(System.out::println);
    }

    @Test
    public void testUpdateByMember(){
        MemberInfoVO memberInfoVO = service.readOne(24);
        memberInfoVO.setPassword("pw"+"수정");
        memberInfoVO.setNickName("nickName"+"수정");
        memberInfoVO.setEmail("email"+"수정"+"@test.com");
        memberInfoVO.setPhoneNum("1234-"+"수정");
        memberInfoVO.setAddress("경기도 성남시 분당구 운중동"+"수정");
        int result = service.modifyByMember(memberInfoVO);
        System.out.println("수정된 개수: "+result);
    }
}
