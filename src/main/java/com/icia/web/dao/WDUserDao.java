
package com.icia.web.dao;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDUser;


public interface WDUserDao
{

	public int userInsert(WDUser wduser);

	public int userUpdate(WDUser wduser);

	public WDUser userSelect(String userId);
	
	public int wdUserIdCount(String userId);
	
	public int checkInsert(WDUser wduser);
	
	public WDUser checkSelect(int uCheck);
	
	public int userDrop(WDUser wduser);
	
	//장바구니 예약번호가 없는 상태에서 결혼예정일 업데이트
	public int nonRezNumberMarrydateUpdate(WDUser wduser);
	
}
