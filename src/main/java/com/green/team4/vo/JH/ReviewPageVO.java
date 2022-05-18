package com.green.team4.vo.JH;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewPageVO {

    List<ReviewVO> list;
    PagingVO pageInfo;
}
