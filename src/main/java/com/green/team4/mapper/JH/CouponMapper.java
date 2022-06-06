package com.green.team4.mapper.JH;

import com.green.team4.vo.JH.CouponVO;
import com.green.team4.vo.JH.MemberCouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {

    public void insert(CouponVO couponVO);

    public void insertCouponToMember(MemberCouponVO memberCouponVO);

    public List<MemberCouponVO> getCouponAvailable(MemberCouponVO memberCouponVO);

    public void updateStatus(MemberCouponVO memberCouponVO);
}
