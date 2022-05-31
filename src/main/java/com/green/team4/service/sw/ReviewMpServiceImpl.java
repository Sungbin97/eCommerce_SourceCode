package com.green.team4.service.sw;

import com.green.team4.mapper.sw.OrderItemMapper;
import com.green.team4.mapper.sw.ReviewFilesMpMapper;
import com.green.team4.mapper.sw.ReviewMpMapper;
import com.green.team4.vo.sw.OrderItemVO;
import com.green.team4.vo.sw.ReviewFilesMpVO;
import com.green.team4.vo.sw.ReviewMpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewMpServiceImpl implements ReviewMpService{

    private final ReviewMpMapper reviewMpMapper;
    private final ReviewFilesMpMapper reviewFilesMpMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public int register(ReviewMpVO reviewMpVO) { // 리뷰 신규 등록
        log.info("ReviewMpService => register 실행 => 받은 reviewMpVO: "+reviewMpVO);

        // 리뷰 글 등록
        int result = reviewMpMapper.insert(reviewMpVO);
        int key = reviewMpVO.getRno();
        log.info("key: "+key);

        // 리뷰 첨부파일 등록
        if(reviewMpVO.getReviewFilesList() != null){
            reviewMpVO.getReviewFilesList().forEach(file->{
                file.setRno(key);
                log.info("등록 파일: "+file);
                reviewFilesMpMapper.insert(file);
            });
        }

        // 해당 주문상품 리뷰 등록 여부 상태 업데이트
        OrderItemVO orderItemVO = orderItemMapper.getOne(reviewMpVO.getOINo());
        orderItemVO.setIReviewStatus("등록완료");
        orderItemMapper.update(orderItemVO);
        return result;
    }

    @Override
    public List<ReviewMpVO> readAllByPno(int pno) { // 리뷰 가져오기 (pno 단위)
        log.info("ReviewMpService => readAllByPno 실행 => 받은 pno: "+pno);

        // 리뷰 글 모두 가져오기
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByMno(pno);
        log.info("ReviewMpService => readAllByPno 실행 후 받은 reviewList: "+reviewList);

        // 리뷰 첨부파일 모두 가져오기
        List<ReviewMpVO> resultList = new ArrayList<>();

        reviewList.forEach(reviewMpVO -> {
            List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(reviewMpVO.getRno());
            reviewMpVO.setReviewFilesList(fileList);
            resultList.add(reviewMpVO);
        });

        return resultList;
    }

    @Override
    public List<ReviewMpVO> readAllByMno(int mno) { // 리뷰 가져오기 (mno 단위)
        log.info("ReviewMpService => readAllByMno 실행 => 받은 mno: "+mno);

        // 리뷰 글 모두 가져오기
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByMno(mno);
        log.info("ReviewMpService => readAllByMno 실행 후 받은 reviewList: "+reviewList);

        // 리뷰 첨부파일 모두 가져오기
        List<ReviewMpVO> resultList = new ArrayList<>();

        reviewList.forEach(reviewMpVO -> {
            List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(reviewMpVO.getRno());
            reviewMpVO.setReviewFilesList(fileList);
            resultList.add(reviewMpVO);
        });
        return resultList;
    }

    @Override
    public ReviewMpVO readOneByRno(int rno) { // 리뷰 하나 가져오기
        log.info("ReviewMpService => readOneByRno 실행 => 받은 rno: "+rno);

        // 리뷰 글 하나 가져오기
        ReviewMpVO reviewMpVO = reviewMpMapper.getOneByRno(rno);

        // 리뷰 첨부파일 가져오기
        List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(rno);

        // 리뷰 글에 첨부파일 저장
        reviewMpVO.setReviewFilesList(fileList);

        return reviewMpVO;
    }

    @Override
    public int modify(ReviewMpVO reviewMpVO) { // 리뷰 수정
        log.info("ReviewMpService => modify 실행 => 받은 reviewMpVO: "+reviewMpVO);
        int result = reviewMpMapper.update(reviewMpVO);
        log.info("ReviewMpService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int remove(int rno) { // 리뷰 삭제
        log.info("ReviewMpService => remove 실행 => 받은 rno: "+rno);
        int result = reviewMpMapper.delete(rno);
        log.info("ReviewMpService => remove 실행 후 삭제된 데이터 개수: "+result);
        return result;
    }
}
