package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.web.dao.WDAdminUserDao;
import com.icia.web.model.WDAdminUser;

@Service("wdAdminUserService")
public class WDAdminUserService 
{
	private static Logger logger = LoggerFactory.getLogger(WDAdminUserService.class);
	
	@Autowired
	private WDAdminUserDao wdAdminUserDao;
	
	//사용자리스트
	public List<WDAdminUser> wdAdmUserList(WDAdminUser adminUser)
	{
		List<WDAdminUser> list = null;
		
		try
		{
			list = wdAdminUserDao.wdAdmUserList(adminUser);
		}	
		catch(Exception e)
		{
			logger.error("[WDAdminUserService] wdAdmUserList Exception", e);
		}
		
		return list;
	}
	
	//사용자 수 조회
	public long wdAdmUserListCount(WDAdminUser adminUser)
	{
		long count = 0;
		
		try
		{
			count = wdAdminUserDao.wdAdmUserListCount(adminUser);
		}
		catch(Exception e)
		{
			logger.error("[WDAdminUserService] wdAdmUserList Exception", e);
		}
		
		return count;
	}
	
	//사용자 조회
	public WDAdminUser wdAdminUserSelect(String userId)
	{
		WDAdminUser wdAdminUser = null;
		
		try
		{
			wdAdminUser = wdAdminUserDao.wdAdminUserSelect(userId);
		}
		catch(Exception e)
		{
			logger.error("[WDAdminUserService] wdAdminUserSelect Exception", e);
		}
		
		return wdAdminUser;
	}
	
	//사용자수정
	public long wdAdmUserUpdate(WDAdminUser adminUser)
	{
		long count = 0;
		
		try
		{
			count = wdAdminUserDao.wdAdmUserUpdate(adminUser);
		}
		catch(Exception e)
		{
			logger.error("[WDAdminUserService] wdAdmUserUpdate Exception", e);
		}
		
		return count;
	}
	
	
}
