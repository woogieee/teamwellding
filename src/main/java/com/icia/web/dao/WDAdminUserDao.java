package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDAdminUser;

@Repository("wdAdminUserDao")
public interface WDAdminUserDao 
{
	//사용자 수 조회
	public long wdAdmUserListCount(WDAdminUser adminUser);
	
	//사용자리스트 조회
	public List<WDAdminUser> wdAdmUserList(WDAdminUser adminUser);
	
	//사용자조회
	public WDAdminUser wdAdminUserSelect(String userId);
	
	//사용자 정보 수정
	public long wdAdmUserUpdate(WDAdminUser adminUser);
}
