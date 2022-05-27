package com.green.team4.controller.bs;

import com.green.team4.service.bs.BoardService;
import com.green.team4.vo.bs.BoardVO;
import com.green.team4.vo.bs.Criteria;
import com.green.team4.vo.bs.PageMaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bs/board/*")
@Log4j2
public class BoardController {
    @Autowired
    BoardService boardService;
    @GetMapping("/list")
    public void list(Model model, Criteria criteria) {
        PageMaker pageMaker = new PageMaker(criteria, boardService.getTotal(criteria));
        log.info("list로 이동....");
        model.addAttribute("items", boardService.getPageList(criteria));
        model.addAttribute("pageMaker",pageMaker);

    }

    @GetMapping("/register")
    public void register() {
        log.info("register로 이동....");
    }

    @PostMapping("/register")
    public String register(BoardVO vo) {
        boardService.insert(vo);
        log.info("게시글 등록 성공");
        return "redirect:list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Model model, Long uNo, Criteria criteria) {
        log.info("read로 이동");
        model.addAttribute("item", boardService.getOne(uNo));
        model.addAttribute("cri",criteria);
    }


    @PostMapping("/modify")
    // redirect시에 컨트롤러가 속성을 선택하는데 사용할 수 있는 모델 인터페이스의 분화. get 방식임.
    // 리다이렉트가 발생하면 원래 요청은 끊어지고, 새로운 HTTP get요청 시작으로 인해 리다이렉트 실행 이전의 모델 데이터는 소멸한다.
    // get방식을 통해 url에 데이터를 붙여 보낼 수 있지만, 보기 지저분하고 보안에 취약할 수 있기 때문에 RedirectAttributes를
    // 사용한다.
    public String modify(BoardVO vo, Criteria criteria, RedirectAttributes redirectAttributes) {
        boardService.modify(vo);
        log.info(criteria);
        redirectAttributes.addAttribute("page",criteria.getPage());
        redirectAttributes.addAttribute("pageNum",criteria.getPageNum());
        log.info("수정 완료");
        return "redirect:/bs/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("uNo") Long uNo){
        boardService.delete(uNo);
        log.info("삭제(수정) 완료");
        return "redirect:list";
    }

    @GetMapping("/index")
    public void index(){
        log.info("index 연결 완료");
    }

    @GetMapping("/blog")
    public void blog(){
        log.info("blog");
    }

}
