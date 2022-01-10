package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDRez;

@Repository("wdRezDao")
public interface WDRezDao {
	
	public WDRez rezList(WDRez wdRez);
	
	public long rezListCount(WDRez wdRez);
	
	public long rezNoInsert(WDRez wdRez);
	
	public long rezHallInsert(WDRez wdRez);
	
	public long rezStudioInsert(WDRez wdRez);
	
	public long rezDressInsert(WDRez wdRez);
	
	public long rezMakeupInsert(WDRez wdRez);
	
	public long rezWDateInsert(WDRez wdRez);
	
	public long rezFullPriceInsert(WDRez wdRez);
	
	//업데이트문은 건수로 리턴하는데 long타입은 너무 불필요하게 큰 데이터 타입이라 int로 변경합니다.
	public int rezStudioUpdate(WDRez wdRez);
	   
	public int rezDressUpdate(WDRez wdRez);
	   
	public int rezMakeupUpdate(WDRez wdRez);
	   
	public int rezHallUpdate(WDRez wdRez);
	 
}
