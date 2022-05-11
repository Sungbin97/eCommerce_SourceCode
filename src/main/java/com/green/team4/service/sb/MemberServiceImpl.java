package com.green.team4.service.sb;

import com.green.team4.mapper.MemberMapper;
import com.green.team4.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    @Override
    public int mno(MemberVO vo) {
        return vo.getMno();
    }

    @Override
    public int insert(MemberVO vo) {
        return memberMapper.insert(vo);
    }

    @Override
    public List<MemberVO> getAll() {
        log.info("전체 회원 조회");
        return memberMapper.getAll();
    }

    @Override
    public MemberVO getOne(int mno) {
        return memberMapper.getOne(mno);
    }

    @Override
    public int update(MemberVO vo) {
        log.info("회원정보 수정");
        return memberMapper.update(vo);
    }

    @Override
    public int delete(int mno) {
        log.info("회원정보 삭제");
        return memberMapper.delete(mno);
    }
}
