package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.web.dao.WDCouponDao;
import com.icia.web.dao.WDRezDao;
import com.icia.web.model.WDRez;

@Service ("wdRezService")
public class WDRezService {

	private static Logger logger = LoggerFactory.getLogger(WDRezService.class);
	
	@Autowired
	private WDRezDao wdRezDao;
	
	@Autowired
	private WDCouponDao wdCouponDao;
	
	//예약 테이블 값 가져오기
	/*
	public WDRez rezSelect(String userId) 
	{
		WDRez wdRez = null;
		
		try 
		{
			wdRez = wdRezDao.rezSelect(userId);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezSelect Exception", e);
		}
		
		return wdRez;
	}
	*/
	public WDRez rezSelect(WDRez wdRez) 
	{
		try 
		{
			wdRez = wdRezDao.rezSelect(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezSelect Exception", e);
		}
		
		return wdRez;
	}
	
	
	//예약 게시물 총 수 
	public long rezListCount()
	{
		long count = 0;
		
		try
		{
			count = wdRezDao.rezListCount();
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezListCount Exception", e);
		}
		return count;
	}
	
	//아이디를 통해 예약건수가 있는지 확인
	public int checkRez(String userId) 
	{
		int count = 0;
		
		try 
		{
			count = wdRezDao.checkRez(userId);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] checkRez");
		}
		
		return count;
	}
	
	//예약 번호를 부여함.
	public long rezNoInsert(WDRez wdRez) 
	{
		long cnt = 0;
		
		try 
		{
			cnt = wdRezDao.rezNoInsert(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezNoInsert Exception", e);
		}
		
		return cnt;
	}
	
	//예약 현황 조회
	public WDRez rezList(WDRez wdRez)
	{
		
		try
		{
			wdRez = wdRezDao.rezList(wdRez);
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
	
	//장바구니에서 홀 제거
	public int rezHallUpdate(WDRez wdRez) 
	{
		int cnt = 0;
		
		try 
		{
			cnt = wdRezDao.rezHallUpdate(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezHallUpdate Exception", e);			
		}
		
		return cnt;
	}
	
	//장바구니에서 스튜디오 제거
	public int rezStudioUpdate(WDRez wdRez) 
	{
		int cnt = 0;
		
		try 
		{
			cnt = wdRezDao.rezStudioUpdate(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezStudioUpdate Exception", e);
		}
		
		return cnt;
	}
	
	//장바구니에서 드레스 제거
	public int rezDressUpdate(WDRez wdRez) 
	{
		int cnt = 0;
		
		try 
		{
			cnt = wdRezDao.rezDressUpdate(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezDressUpdate Exception", e);
		}
		
		return cnt;
	}
	
	//장바구니에서 메이크업 제거
	public int rezMakeupUpdate(WDRez wdRez) 
	{
		int cnt = 0;
		
		try 
		{
			cnt = wdRezDao.rezMakeupUpdate(wdRez);
		}
		catch(Exception e) 
		{
			logger.error("[WDRezService] rezMakeupUpdate Exception", e);
		}
		
		return cnt;
	}
	
	
	//카카오페이 성공 시 업데이트 문, 쿠폰까지 넣어주고, 사용한 쿠폰도 업데이트 해야 함.
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int rezUpdatePay(WDRez wdRez) throws Exception
	{
		int count = 0;
		

		count = wdRezDao.rezUpdatePay(wdRez);
		
		if(count > 0) 
		{
			logger.debug("[여긴타니!!!!!1????]");
			int cnt = wdCouponDao.couponStatusUpdate(wdRez.getUserId());
			
			if(cnt <= 0) 
			{
				count = 0;
			}
			else 
			{
				int x = wdRezDao.rezUpdateStatusAfC(wdRez.getUserId());
				if( x <= 0) 
				{
					count = 0;
				}
			}
		}
		
		return count;
	}

	public int rezCancelPayment(String userId) {
		
		int count = 0;
		
		try
		{
			count = wdRezDao.rezCancelPayment(userId);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezCancelPayment", e);
		}
		
		return count;
	}
	
	public List<WDRez> rezCancelSelect (WDRez wdRez)
	{
		List<WDRez> list = null;
		
		try
		{
			list = wdRezDao.rezCancelSelect(wdRez);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezCancelSelect");
		}
		
		return list;
	}
	
	public int rezPointReturn(String userId) {
	
		int count = 0;
		
		try
		{
			count = wdRezDao.rezPointReturn(userId);
		}
		catch(Exception e)
		{
			logger.error("[WDRezService] rezPointReturn", e);
		}
		
		return count;
	}
}
