package com.icia.web.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDCoupon;

@Repository("wdCouponDao")
public interface WDCouponDao {
   
   public List<WDCoupon> couponSelectList(String userId);
   
   //결제 시 , 쿠폰 정보를 가져와서 해당 유저의 해당 쿠폰 업데이트
   public int couponStatusUpdate(String userId);

   //결제 완료된 내역에서 결제중 쿠폰 정보를 가져오기 시작
   public WDCoupon couponSelectPayOk(HashMap<String, Object> map);
}

