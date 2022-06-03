package com.green.team4.vo.JH;

import com.green.team4.vo.sw.ReviewMpVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewPageVO {

    List<ReviewMpVO> list;
    PagingVO pageInfo;
}
