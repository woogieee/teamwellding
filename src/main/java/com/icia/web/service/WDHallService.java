package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.web.dao.WDHallDao;
import com.icia.web.dao.WDRezDao;
import com.icia.web.model.WDDress;
import com.icia.web.model.WDHall;
import com.icia.web.model.WDHallFile;
import com.icia.web.model.WDRez;

@Service("WDHallService")
public class WDHallService {
   
   private static Logger logger = LoggerFactory.getLogger(WDHallService.class);
   
   @Autowired
   private WDHallDao wdHallDao;
   
   @Autowired
   private WDRezDao wdRezDao;
   
   //랭킹은 인덱스 컨트롤러에서 불러오자!!
   public List<WDHall> WDHallRanking(){
      
      List<WDHall> list = null;
      
      try 
      {
            list = wdHallDao.WDHallRanking();
      }
      catch(Exception e) 
      {
         logger.error("[HallService] WDHallRanking Exception : ", e);
      }      
      
      return list;
   }
   
   public WDHall WDHallSelect(WDHall wdHall) {
      
      try {
         wdHall = wdHallDao.WDHallSelect(wdHall);
      }
      catch(Exception e) {
         logger.error("[HallService] WDHallSelect Exception : ", e);
      }
      
      return wdHall;
   }
   
   public WDHallFile WDHallFileSelect(WDHallFile wdHallFile) {
      
      try {
         wdHallFile = wdHallDao.WDHallFileSelect(wdHallFile);
         
      }
      catch(Exception e) {
         logger.error("[HallService] WDHallFileSelect Exception : ", e);
      }
      
      return wdHallFile;
   }
   
   public List<WDHall> WDHallList(WDHall wdHall){
      
      List<WDHall> list = null;
      
      try {
         list = wdHallDao.WDHallList(wdHall);
      }
      catch(Exception e) {
         logger.error("[HallService] WDHallList Exception : ", e);
      }
      return list;
   }
   
   public long WDHallListCount(WDHall wdHall) {
      
      long count = 0;
      
      try {
         count = wdHallDao.WDHallListCount(wdHall);
      }
      catch(Exception e) {
         logger.error("[HallService] WDHallListCount Exception : ", e);
      }
      
      return count;
   }
   
	//동일 업체 홀 정보 가져오기.
   	public List<WDHall> hallSameCom(WDHall wdHall)
	{
		List<WDHall> sameCom = null;
	
		try 
		{
			sameCom = wdHallDao.hallSameCom(wdHall);
		}
		catch(Exception e)
		{
			logger.error("[WDHallService] hallSameCom Exception", e);
		}
		
		return sameCom;
	}
	
   	
   	//아이디로 조회했는데, 예약번호도 없고, 홀 코드도 없음. 트랜잭션으로 인서트 업데이트 한번에 진행
   	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
   	public long rezNoHallTotalInsert(WDRez wdRez) throws Exception
   	{
   		long cnt = 0;
   		//인서트
   		if( wdRezDao.rezNoInsert(wdRez) > 0) 
   		{
   			//예약번호 부여됨.
   			//홀 업데이트
   			cnt = wdRezDao.rezHallInsert(wdRez);
   		}
   		return cnt;
   	}
   	
   	//웨딩홀 추가 쿼리문
   	public int weddinghallInsert(WDHall wdHall) 
   	{
   		int count = 0;
   		
   		try {
   			count = wdHallDao.weddinghallInsert(wdHall);
   		}
		catch(Exception e)
		{
			logger.error("[WDHallService] weddinghallInsert Exception", e);
		}
   		
   		return count;
   	}
   	
   	//웨딩홀 마지막 코드 불러오기
   	public String maxWHCode() {
   		
   		String whCode = "";
   		
   		try {
   			whCode = wdHallDao.maxWHCode();
   		}
		catch(Exception e)
		{
			logger.error("[WDHallService] weddinghallInsert Exception", e);
		}
   		
   		return whCode;
   	}
}