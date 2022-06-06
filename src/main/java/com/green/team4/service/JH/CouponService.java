package com.green.team4.service.JH;

import com.green.team4.vo.JH.CouponVO;
import com.green.team4.vo.JH.MemberCouponVO;

import java.util.List;

public interface CouponService {

    public void insert(CouponVO couponVO);
    public void insertCouponToMember(MemberCouponVO memberCouponVO);
    public List<MemberCouponVO> getCouponAvailable(MemberCouponVO memberCouponVO);
    public void updateStatus(MemberCouponVO memberCouponVO);
}
