package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDRez;

@Repository("wdRezDao")
public interface WDRezDao {
	
	//public WDRez rezSelect(String userId);
	public WDRez rezSelect(WDRez wdRez);
	
	//예약 내역 조회를 위한 셀렉트 메소드
	public WDRez rezList(WDRez wdRez);
	
	public long rezListCount();
	
	//아이디로 예약현황 확인. 1개라도 있으면 추가 예약번호 부여하지 않음.
	public int checkRez(String userId);
	
	//작성중이던 예약 기록이 없거나 예약이 결제완료 상태면 예약번호 생성
	public long rezNoInsert(WDRez wdRez);
	
	//예약번호와 아이디를 비교하여 홀 데이터 삽입 시작
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
	
	//카카오페이 성공 시 업데이트 문
	public int rezUpdatePay(WDRez wdRez);
	
	//쿠폰까지 삽입되면, 그제서야 상태 Y로 변경
	public int rezUpdateStatusAfC(String userId);
	
	//결제 완료된 내역 가져오기
	public List<WDRez> rezSelectList(String userId);
	
	//결제 취소 
	public int rezCancelPayment(String userId);
		
	//결제 취소 리스트(관리자가 확인하는 리스트)
	public List<WDRez> rezCancelSelect(WDRez wdRez);
		
	//결제 후 결제 취소시 포인트로 환급
	public int rezPointReturn(String userId);
	
}
