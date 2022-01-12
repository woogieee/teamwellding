package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDCoupon;

@Repository("wdCouponDao")
public interface WDCouponDao {
	
	public List<WDCoupon> couponSelectList(String userId);
	
	//결제 시 , 쿠폰 정보를 가져와서 해당 유저의 해당 쿠폰 업데이트
	public int couponStatusUpdate(String userId);


}
