package com.green.team4.controller.community;

import com.green.team4.service.community.ReplyService;
import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.community.PageMaker;
import com.green.team4.vo.community.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/community/reply/*")
@Log4j2
public class ReplyController {
    @Autowired
    ReplyService replyService;


    @PostMapping(path="register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> register(@RequestBody ReplyVO replyVO){
        log.info("@@@replyRegister.replyVO: "+replyVO);
        try {
            replyService.insert(replyVO);
            //제대로 등록되었으면 "ReplyRegisterOK" 문자열과 HTTP 상태 정상
            return new ResponseEntity<>("ReplyRegisterOK", HttpStatus.OK);
        } catch(Exception e) {
            //제대로 등록 안 되었으면 예외메시지와 HTTP 상태 400
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getList/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> getCommentList(@PathVariable("bno") Long bno, @PathVariable("page") Long page){
        Map<String,Object> map = new HashMap<>();
        Criteria criteria = new Criteria(page*10-9,3L);
        PageMaker pageMaker = new PageMaker(criteria, replyService.getTotal(criteria));
        map.put("pageMaker",pageMaker);
        map.put("list",replyService.getPageList(criteria,bno));
        try {
            log.info(replyService.getPageList(criteria,bno));
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "getOne", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplyVO> getOne(@RequestParam("rno") Long rno){
        try {
            log.info(replyService.getOne(rno));
            return new ResponseEntity<>(replyService.getOne(rno),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "update/{rno}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ResponseEntity<String> getList(
            @RequestBody ReplyVO replyVO,
            @PathVariable("rno") Long rno){
        replyVO.setRno(rno);
        replyService.modify(replyVO);

    return new ResponseEntity<>("update success"+ replyVO.getReply(),HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{rno}",
    produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> get(@PathVariable("rno") Long rno) {
        replyService.delete(rno);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


}
