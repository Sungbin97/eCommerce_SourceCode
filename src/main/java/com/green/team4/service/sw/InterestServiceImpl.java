package com.green.team4.service.sw;

import com.green.team4.mapper.sw.InterestMapper;
import com.green.team4.vo.sw.InterestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService{

    // 의존성 주입
    private final InterestMapper interestMapper;

    @Override
    public int register(InterestVO interestVO) { // 찜목록 신규 등록
        log.info("InterestService => register 실행 => 받은 interestVO: "+interestVO);
        int result = interestMapper.insert(interestVO);
        log.info("InterestService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override
    public List<InterestVO> readAll(int mno) { // 찜목록 전체 가져오기(mno 단위)
        log.info("InterestService => readAll 실행 => 받은 mno: "+mno);
        List<InterestVO> list = interestMapper.getAll(mno);
        log.info("InterestService => readAll 실행 후 받은 list: "+list);
        return list;
    }

    @Override
    public int remove(int mno, int pno) { // 찜목록 하나 삭제
        log.info("InterestService => remove 실행 => 받은 mno: "+mno);
        log.info("InterestService => remove 실행 => 받은 pno: "+pno);
        int result = interestMapper.delete(mno,pno);
        log.info("InterestService => remove 실행 => 삭제된 데이터 개수: "+result);
        return result;
    }
}
