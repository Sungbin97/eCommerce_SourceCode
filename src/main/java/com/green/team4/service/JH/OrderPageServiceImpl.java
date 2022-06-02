package com.green.team4.service.JH;

import com.green.team4.mapper.JH.OrderPageMapper;
import com.green.team4.mapper.JH.ShopMapper;
import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.vo.JH.DBOrderItemVO;
import com.green.team4.vo.JH.DBOrderVO;
import com.green.team4.vo.JH.OrderPageItemVO;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sw.MemberInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            OrderPageItemVO productsInfo = orderPageMapper.getProductsInfoWithOpt(product_optVO);
            log.info("productsInfo: "+productsInfo);
            productsInfo.setPOptionPrice(order.getPOptionPrice());
            productsInfo.setItemCount(order.getItemCount());
            log.info("productsInfo2 : "+productsInfo);
            productsInfo.initSaleTotal();
            log.info("dsad : "+productsInfo);
            list.add(productsInfo);

        }
        return list;
    }

    @Override
    @Transactional
    public void order(DBOrderVO vo) {
        System.out.println("order서비스 입장");
        System.out.println("vo : " + vo);
        //회원정보
        MemberInfoVO member =memberInfoMapper.getMemberInfo(vo.getId());
        System.out.println("member: " + member);
        //주문정보(order테이블에 넣을 데이터 만들기)
        List<DBOrderItemVO> ords = new ArrayList<>();
        for(DBOrderItemVO order:vo.getOrders()){
            System.out.println("order :"+order);
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            DBOrderItemVO orderItem = orderPageMapper.getOrderInfoWithOpt(product_optVO);
            System.out.println("orderItem : "+orderItem);
            orderItem.setICount(order.getICount());
            orderItem.setOno(vo.getOno());
            orderItem.setPOptionPrice(order.getPOptionPrice());
            orderItem.initSaleTotal();
            System.out.println("orderItem : "+orderItem);
            vo.setPno(order.getPno());
            ords.add(orderItem);


        }
        vo.setPhoneNum(member.getPhoneNum());
        vo.setOrders(ords);
        vo.getOrderPriceInfo();

        //db에 넣기
        orderPageMapper.enrollOrder(vo);
        for(DBOrderItemVO order : vo.getOrders()){
            System.out.println("db 넣기 order : " + order);
            orderPageMapper.enrollOrderItem(order);
        }
        //포인트 차감
        int calPoint = member.getPoint();
        calPoint = calPoint - vo.getTUsePoint() + vo.getTSavePoint();
        member.setPoint(calPoint);
        orderPageMapper.deductMoney(member);

        //재고 차감
        for(DBOrderItemVO order: vo.getOrders()){
            System.out.println("재고입장");
            System.out.println("order : " + order);
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            System.out.println(product_optVO);
            Product_optVO product_optVO2 = shopMapper.getProductWithOpt(product_optVO);
            System.out.println("하이");
            product_optVO.setPAmount(product_optVO2.getPAmount() - order.getICount());
            System.out.println(product_optVO);
            orderPageMapper.deductStockWithOpt(product_optVO);


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
