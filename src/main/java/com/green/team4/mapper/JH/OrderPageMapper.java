package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.DBOrderItemVO;
import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.OrderPageItemVO;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.MemberVO;
import com.green.team4.vo.sb.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderPageMapper {
    
    //주문 상품 정보
    public OrderPageItemVO getProductsInfo(int pno);

    // 주문 상품 정보 (주문처리용)
    public DBOrderItemVO getOrderInfo(int pno);

    //주문 테이블에 주문 등록하기
    public int enrollOrder(DBOrderVO vo);

    // 주문 상품 등록
    public int enrollOrderItem(DBOrderItemVO vo);

    //회원 돈,포인트 차감
    public int deductMoney(MemberVO memberVO);

    //주문 재고 차감
    public int deductStock(ProductVO pvo);

//    /* 장바구니 제거(주문한거만) */
//    public int deleteOrderCart(장바구니 VO);
}
