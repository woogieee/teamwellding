package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDMakeUp;

@Repository("WDMakeUpDao")
public interface WDMakeUpDao 
{
	//메이크업 업체 총 수 시작
	public long makeUpListCount(WDMakeUp wdMakeUp);
	
	//메이크업 업체 총 수 시작 - 예약내역 제외
	public long makeUpListCountmr(WDMakeUp wdMakeUp);
	
	//메이크업 목록 불러오기
	public List<WDMakeUp> makeUpList(WDMakeUp wdMakeUp);
	
	//메이크업 목록 불러오기-예약내역 제외
	public List<WDMakeUp> makeUpListMinusRez(WDMakeUp wdMakeUp);
	
	//메이크업 상세보기페이지
	public WDMakeUp makeupSelect(String mCode);
	
	//메이크업 글쓰기
	   public int makeupInsert(WDMakeUp wdMakeUp);
	   
   //메이크업 m코드 불러오기
   public String makeupMax();
   
   //메이크업 수정
   public int makeupUpdate(WDMakeUp wdMakeUp);
	
}
