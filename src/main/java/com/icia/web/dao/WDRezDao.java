package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDRez;

@Repository("wdRezDao")
public interface WDRezDao {
	
	public WDRez rezList(String userId);
	
	public long rezListCount(WDRez wdRez);
	
	public long rezNoInsert(WDRez wdRez);
	
	public long rezHallInsert(WDRez wdRez);
	
	public long rezStudioInsert(WDRez wdRez);
	
	public long rezDressInsert(WDRez wdRez);
	
	public long rezMakeupInsert(WDRez wdRez);
	
	public long rezWDateInsert(WDRez wdRez);
	
	public long rezFullPriceInsert(WDRez wdRez);
	
	public long rezStudioUpdate(WDRez wdRez);
	   
	public long rezDressUpdate(WDRez wdRez);
	   
	public long rezMakeupUpdate(WDRez wdRez);
	   
	public long rezHallUpdate(WDRez wdRez);
}
