package com.green.team4.service.sw;

import com.green.team4.mapper.sw.ExchangeFilesMapper;
import com.green.team4.mapper.sw.ExchangeMapper;
import com.green.team4.vo.sw.ExchangeFilesVO;
import com.green.team4.vo.sw.ExchangeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

    // 의존성 주입 (첨부파일 Mapper 여기서 한번에 작업)
    private final ExchangeMapper exchangeMapper;
    private final ExchangeFilesMapper exchangeFilesMapper;

    @Override
    public int register(ExchangeVO exchangeVO) { // 취소/반품/교환 신규 등록
        // 신청서 등록
        log.info("ExchangeService => register 실행 => 받은 exchangeVO: "+exchangeVO);
        int result = exchangeMapper.insert(exchangeVO);
        int key = exchangeVO.getEno();
        log.info("key: "+key);

        // 신청서 첨부파일 등록
        exchangeVO.getExchangeFilesList().forEach(file->{
            file.setEno(key); // 신청서 DB 등록된 PK 바로 가져와서 이미지 데이터에 저장
            log.info("등록 파일: "+file);
            exchangeFilesMapper.insert(file);
        });
        return result;
    }

    @Override
    public List<ExchangeVO> readAll(int mno) { // 취소/반품/교환 내역 모두 가져오기(mno단위)

        // 신청서 모두 가져오기
        log.info("ExchangeService => readAll 실행 => 받은 mno: "+mno);
        List<ExchangeVO> exchangeList = exchangeMapper.getAll(mno);
        log.info("ExchangeService => readAll 실행 후 받은 exchangeList: "+exchangeList);

        // 신청서 첨부파일 모두 가져오기
        List<ExchangeVO> resultList = new ArrayList<>();

        exchangeList.forEach(exchangeVO -> {
            List<ExchangeFilesVO> fileList = exchangeFilesMapper.getAll(exchangeVO.getEno()); // 신청서 첨부파일 가져오기
            exchangeVO.setExchangeFilesList(fileList); // 신청서에 가져온 첨부파일 저장
            resultList.add(exchangeVO); // 신청서List에 저장
        });

        return resultList;
    }

    @Override
    public ExchangeVO readOne(int eno) { // 취소/반품/교환 내역 하나 가져오기

        // 신청서 하나 가져오기
        log.info("ExchangeService => readOne 실행 => 받은 eno: "+eno);
        ExchangeVO exchangeVO = exchangeMapper.getOne(eno);
        log.info("ExchangeService => readOne 실행 후 받은 exchangeVO: "+exchangeVO);

        // 신청서 첨부파일 가져오기
        List<ExchangeFilesVO> fileList = exchangeFilesMapper.getAll(eno);

        // 신청서에 가져온 첨부파일 저장
        exchangeVO.setExchangeFilesList(fileList);

        return exchangeVO;
    }

    @Override
    public int modify(ExchangeVO exchangeVO) { // 취소/반품/교환 내역 수정
        log.info("ExchangeService => modify 실행 => 받은 exchangeVO: "+exchangeVO);
        int result = exchangeMapper.update(exchangeVO);
        log.info("ExchangeService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int remove(int eno) { // 취소/반품/교환 내역 삭제
        log.info("ExchangeService => remove 실행 => 받은 eno: "+eno);

        // 신청서 첨부파일 삭제
        exchangeFilesMapper.delete(eno);

        // 신청서 삭제
        int result = exchangeMapper.delete(eno);
        log.info("ExchangeService => remove 실행 후 삭제된 데이터 개수: "+result);

        return result;
    }
}
