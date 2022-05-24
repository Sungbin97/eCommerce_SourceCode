package com.green.team4.vo.sw;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeVO {

    // tbl_exchange 에서 가져올 data
    private int mno; // 회원번호(tbl_memberInfo)
    private String ono; // 주문번호(tbl_order)
    private int pno; // 상품번호(tbl_product)
    private int eno; // 취소/반품/교환 번호
    private String exCategory; // 분류(취소/반품/교환 중 택1)  -- 수정 가능
    private String exContent; // 사유 내용 -- 수정 가능
    private String exStatus; // 진행 상태(접수중, 접수완료, 처리완료)
    private LocalDateTime exDate; // 신청일
    private LocalDateTime exEndDate; // 처리 완료일

    // tbl_product 에서 join 으로 가져올 data
    private String pName; // 상품명

    // tbl_orderItem 에서 join 으로 가져올 data (ono/pno가 같은 것)
    private int iDisPrice; // 상품가(할인 후)
    private int iCount; // 상품 개수
    private int iTotalPrice; // 상품 총 금액

    // 이미지 리스트
    private List<ExchangeFilesVO> exchangeFilesList;
}
