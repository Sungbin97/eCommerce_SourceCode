package com.green.team4.controller.bs;

import com.green.team4.service.bs.ReplyService;
import com.green.team4.vo.bs.Criteria;
import com.green.team4.vo.bs.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bs/board/*")
@Log4j2
public class ReplyController {
    @Autowired
    ReplyService replyService;


    @PostMapping(path="read",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public void register(@RequestBody ReplyVO replyVO){
        log.info(replyVO);
        replyService.insert(replyVO);
    }

    @GetMapping(value = "getCommentList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReplyVO> getCommentList(Long uNo){
        Criteria criteria = new Criteria();
        log.info("getCommentList 입니당");
        log.info(uNo);
        log.info(replyService.getPageList(criteria,uNo));
        return replyService.getPageList(criteria,uNo);
    }

    @GetMapping(value = "/{rno}",
    produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
        log.info("get: " + rno);
        return new ResponseEntity<>(replyService.getOne(rno),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> delete(@PathVariable("rno") Long rno){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
