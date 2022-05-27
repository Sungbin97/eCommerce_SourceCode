package com.green.team4.vo.bs;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {

    private int startPage;
    private int endPage;
    private boolean prev, next;
    private int total;
    private Criteria criteria;
    // 하단 페이지 번호의 갯수
    public PageMaker(Criteria cri,int total){
        this.criteria = cri;
        this.total = total;
        // Math.ceil : 올림
        this.endPage = (int)(Math.ceil((double)(criteria.getPage())/(double)10) * 10);
        this.startPage = this.endPage - 10 + 1;
        int realEnd = (int)(Math.ceil(total/(double)10) * 10);
        if(endPage>realEnd){
            endPage = realEnd;
        }
        prev = startPage > 1;
        next = endPage * cri.getPageNum() < total;
    }
}
