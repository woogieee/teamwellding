package com.icia.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.web.dao.WDAdminDao;
import com.icia.web.model.WDAdmin;

@Service("wdAdminService")
public class WDAdminService 
{
	//클래스 변수정의
	private static Logger logger = LoggerFactory.getLogger(WDAdminService.class);
	
	//데이터베이스 연결할객체
	@Autowired
	private WDAdminDao wdAdminDao;
	
	//관리자 아이디 조회
	public WDAdmin wdAdminSelect(String adminId)
	{
		WDAdmin wdAdmin = null;
		
		try
		{
			wdAdmin = wdAdminDao.wdAdminSelect(adminId);
		}
		catch(Exception e)
		{
			logger.error("[WDAdminService] wdAdminSelect Exception", e);
		}
		
		return wdAdmin;
	}
}
