package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.web.dao.WDRezDao;
import com.icia.web.model.WDRez;

@Service ("wdRezService")
public class WDRezService {

	private static Logger logger = LoggerFactory.getLogger(WDRezService.class);
	
	@Autowired
	private WDRezDao wdRezDao;
	
	//예약 게시물 총 수 
	public long rezListCount(WDRez wdRez)
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezListCount(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezListCount Exception", e);
		}
		return count;
	}
	
	//예약 현황 조회
	public WDRez rezList(String userId)
	{
		WDRez wdRez = null;
		
		try
		{
			wdRez = wdRezDao.rezList(userId);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezList Exception", e);
		}
		return wdRez;
	}
	
	//홀 예약 추가
	public long rezHallInsert(WDRez wdRez)
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezHallInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
	
	//스튜디오 예약 추가
	public long rezStudioInsert(WDRez wdRez)
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezStudioInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
	
	//드레스 예약 추가
	public long rezDressInsert(WDRez wdRez)
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezDressInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
	
	//메이크업 예약 추가
	public long rezMakeupInsert(WDRez wdRez)
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezMakeupInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
	
	//예약날짜 추가
	public long rezWDateInsert(WDRez wdRez)
	{
			long count = 0;
		
		try
		{
			count = wdRezDao.rezWDateInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
	
	//예약 총 가격 
	public long rezFullPriceInsert(WDRez wdRez)
	{
			long count = 0;
		try
		{
			count = wdRezDao.rezFullPriceInsert(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezHallInsert Exception", e);
		}
		
		return count;
	}
}
