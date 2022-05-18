package com.green.team4.vo.JH;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderPageVO {
    private List<OrderPageItemVO> orders;
}
