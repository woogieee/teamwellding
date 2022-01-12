package com.icia.web.dao;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDAdmin;

@Repository("wdAdminDao")
public interface WDAdminDao 
{
	//관리자 아이디 조회
	public WDAdmin wdAdminSelect(String adminId);
}
