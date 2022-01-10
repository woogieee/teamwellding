package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.icia.web.dao.WDExpertDao;
import com.icia.web.model.WDExpert;



@Service("WDExpertService")
public class WDExpertService 
{
	private static Logger logger = LoggerFactory.getLogger(WDExpertService.class);
	
	@Autowired
	private WDExpertDao wdExpertDao;
	
	//파일저장 디렉토리
	@Value("#{env['upload.save.dir']}")
	private String UPLOAD_SAVE_DIR;
	
	//총 수
	public long expertListCount(WDExpert wdExpert) 
	{
		long count = 0;
		
		try 
		{
			count = wdExpertDao.expertListCount(wdExpert);
		}
		catch(Exception e) 
		{
			logger.error("[WDExpertService] expertListCount Exception", e);
		}
		
		return count;
	}

	
	//리스트
	public List<WDExpert> expertList(WDExpert wdExpert)
	{
		List<WDExpert> list = null;
		
		try 
		{
			list = wdExpertDao.expertList(wdExpert);
		}
		catch(Exception e) 
		{
			logger.error("[WDExpertService] expertList Exception", e);
		}
		
		return list;
	}
	
	//상세보기
	public WDExpert expertSelect(String eCode)
	{
		WDExpert wdExpert = null;
		
		try 
		{
			wdExpert = wdExpertDao.expertSelect(eCode);
		}
		catch(Exception e) 
		{
			logger.error("[WDExpertService] expertSelect Exception", e);
		}
		
		return wdExpert;
	}
	
	
}
