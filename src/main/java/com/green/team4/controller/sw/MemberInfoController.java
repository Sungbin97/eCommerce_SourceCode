package com.green.team4.controller.sw;

import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.service.sw.PaymentService;
import com.green.team4.service.sw.ShipmentService;
import com.green.team4.vo.sw.MemberInfoVO;
import com.green.team4.vo.sw.PaymentVO;
import com.green.team4.vo.sw.ShipmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sw/mypage/memberInfo/*")
@RequiredArgsConstructor
public class MemberInfoController {

    // 의존성 주입
    private final MemberInfoService memberInfoService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;


    // memberInfo -----------------------------------------------------------------------------------------

    @GetMapping("/read") // 개인정보, 배송지 정보 가져오기(개인정보 수정화면 포함됨)
    public void readOne(int mno, Model model){
        log.info("MemberInfoController => readOne(GET) 실행 => 받은 mno: "+mno);

        // MemberInfo 가져오기
        MemberInfoVO memberInfoVO = memberInfoService.readOne(mno);
        model.addAttribute("memberInfo",memberInfoVO);

        // ShipmentInfo 가져오기
        List<ShipmentVO> shipList = shipmentService.readAll(mno);
        model.addAttribute("shipmentList",shipList);

        // PaymentInfo 가져오기
        List<PaymentVO> payList = paymentService.readAll(mno);
        model.addAttribute("paymentList",payList);

    }

    @PostMapping("/memModify") // 개인정보 수정 진행
    public void memberModify(MemberInfoVO memberInfoVO, Model model){
        log.info("MemberInfoController => memberModify(POST) 실행 => 받은 MemberInfoVO: "+memberInfoVO);
        memberInfoService.modify(memberInfoVO);
        model.addAttribute("memberInfo",memberInfoVO);
    }

    @GetMapping("/memDelete") // 회원탈퇴 화면 가져오기
    public void memberDelete(int mno, Model model){ // 회원 탈퇴 화면 가져오기
        log.info("MemberInfoController => delete(GET) 실행 => 받은 mno: "+mno);
        model.addAttribute("mno",mno);
        log.info("회원탈퇴 화면 가져오기");
    }

    @PostMapping("/memDelete") // 회원 탈퇴 진행
    public String delete(int mno, String delCategory, String delContent){
        log.info("MemberInfoController => delete(POST) 실행 => 받은 mno: "+mno);
        log.info("MemberInfoController => delete(POST) 실행 => 받은 delCategory: "+delCategory);
        log.info("MemberInfoController => delete(POST) 실행 => 받은 delContent: "+delContent);
        memberInfoService.remove(mno,delCategory,delContent);
        return "redirect:/sw/mypage/main?mno="+1; // ★ 추후 웹사이트 메인페이지로 돌아가는 것으로 수정 예정
    }

    // shipmentInfo -----------------------------------------------------------------------------------------

    @PostMapping("/shipAdd") // 배송지 주소 신규 추가 진행
    public ResponseEntity<Integer> shipAddAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipAdd(POST/AJAX) 실행 => 받은 shipmentVO: "+shipmentVO);
        int addCnt = shipmentService.register(shipmentVO);
        return new ResponseEntity<>(addCnt, HttpStatus.OK);
    }

    @PostMapping("/shipModify") // 배송지 수정 진행
    public ResponseEntity<Integer> shipModifyAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipModify(POST) 실행 => 받은 shipmentVO: "+shipmentVO);
        int modCnt = shipmentService.modify(shipmentVO);
        return new ResponseEntity<>(modCnt,HttpStatus.OK);
    }

    @PostMapping("/shipDelete") // 배송지 삭제 진행
    public ResponseEntity<Integer> shipDeleteAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipDelete(POST) 실행 => 받은 shipmentVO: "+shipmentVO);
        log.info("삭제 대상 sno: "+shipmentVO.getSno());
        int delCnt = shipmentService.remove(shipmentVO.getSno());
        return new ResponseEntity<>(delCnt,HttpStatus.OK);
    }

    // paymentInfo -----------------------------------------------------------------------------------------

    @PostMapping("/payAdd") // 결제수단 정보 신규 추가 진행
    public ResponseEntity<Integer> payAdd(@RequestBody PaymentVO paymentVO){
        log.info("MemberInfoController => payAdd(POST/AJAX) 실행 => 받은 paymentVO: "+paymentVO);
        int addCnt = paymentService.register(paymentVO);
        return new ResponseEntity<>(addCnt,HttpStatus.OK);
    }

    @PostMapping("/payModify") // 결제수단 정보 수정 진행
    public ResponseEntity<Integer> payModify(@RequestBody PaymentVO paymentVO){
        log.info("MemberInfoController => payModify(POST/AJAX) 실행 => 받은 paymentVO: "+paymentVO);
        int modCnt = paymentService.modify(paymentVO);
        return new ResponseEntity<>(modCnt,HttpStatus.OK);
    }

    @PostMapping("/payDelete") // 결제수단 정보 삭제 진행
    public ResponseEntity<Integer> payDelete(@RequestBody PaymentVO paymentVO){
        log.info("MemberInfoController => payDelete(POST/AJAX) 실행 => 받은 paymentVO: "+paymentVO);
        int delCnt = paymentService.remove(paymentVO.getPno());
        return new ResponseEntity<>(delCnt,HttpStatus.OK);
    }
}
