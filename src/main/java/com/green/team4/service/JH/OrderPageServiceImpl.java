package com.green.team4.service.JH;

import com.green.team4.mapper.JH.OrderPageMapper;
import com.green.team4.mapper.JH.ShopMapper;
import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.JH.DBOrderItemVO;
import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.OrderPageItemVO;
import com.green.team4.vo.sb.MemberVO;
import com.green.team4.vo.sb.ProductVO;
import com.green.team4.vo.sw.MemberInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderPageServiceImpl implements OrderPageService {

    @Autowired
    private OrderPageMapper orderPageMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ShopMapper shopMapper;
    //장바구니 매퍼도 나중에 추가

    @Override
    public List<OrderPageItemVO> getProductListInfo(List<OrderPageItemVO> orders) {
        List<OrderPageItemVO> list = new ArrayList<>();
        for(OrderPageItemVO order  :orders){
            System.out.println("페이지 orders : " + order);
//            Product_optVO product_optVO = new Product_optVO();
//            product_optVO.setPno(order.getPno());
//            product_optVO.setPColor(order.getPColor());
           OrderPageItemVO productsInfo = orderPageMapper.getProductsInfo(order.getPno());
           System.out.println("productsInfo: "+productsInfo);
           productsInfo.setItemCount(order.getItemCount());
           productsInfo.initSaleTotal();
           list.add(productsInfo);
        }
        return list;
    }

    @Override
    @Transactional
    public void order(DBOrderVO vo) {
        System.out.println("order서비스 입장");
        //회원정보
        MemberInfoVO member =memberInfoMapper.getMemberInfo(vo.getId());
        System.out.println("member: " + member);
        //주문정보(order테이블에 넣을 데이터 만들기)
        List<DBOrderItemVO> ords = new ArrayList<>();
        for(DBOrderItemVO order:vo.getOrders()){
            System.out.println("order :"+order);
//            Product_optVO product_optVO = new Product_optVO();
//            product_optVO.setPno(order.getPno());
//            product_optVO.setPColor(order.getPColor());
            DBOrderItemVO orderItem = orderPageMapper.getOrderInfo(order.getPno());
            orderItem.setItemCount(order.getItemCount());
            orderItem.setOno(vo.getOno());
            orderItem.initSaleTotal();
            System.out.println("orderItem : "+orderItem);
            ords.add(orderItem);
        }
        vo.setOrders(ords);
        vo.getOrderPriceInfo();

        //db에 넣기
        orderPageMapper.enrollOrder(vo);
        for(DBOrderItemVO order : vo.getOrders()){
            orderPageMapper.enrollOrderItem(order);
        }
        //포인트 차감
        int calPoint = member.getPoint();
        calPoint = calPoint - vo.getUsePoint() + vo.getOrderSavePoint();
        member.setPoint(calPoint);
        orderPageMapper.deductMoney(member);

        //재고 차감
        for(DBOrderItemVO order: vo.getOrders()){
            ProductVO productVO = shopMapper.getOne(order.getPno());
            productVO.setPAmount(productVO.getPAmount() - order.getItemCount());
            orderPageMapper.deductStock(productVO);
        }
//        // 장바구니제거
//        for(DBOrderItemVO order : vo.getOrders()) {
//            CartDTO dto = new CartDTO();
//            dto.setMemberId(ord.getMemberId());
//            dto.setBookId(oit.getBookId());
//
//            cartMapper.deleteOrderCart(dto);
//        }
    }
}
