package com.icia.web.dao;

import org.springframework.stereotype.Repository;

@Repository("WDExpertDao")
public interface WDExpertDao 
{
	//총 수
	public long expertListCount(WDExpert wdExpert);
	
	//리스트보기
	public List<WDExpert> expertList(WDExpert wdExpert);
	
	//상세보기
	public WDExpert expertSelect(String eCode);
}
