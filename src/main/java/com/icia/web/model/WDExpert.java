package com.icia.web.model;

import java.io.Serializable;

public class WDExpert implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String eId;
	private String eCode;
	private String ePwd;
	private String eName;
	private String eEmail;
	private String eLocation;
	private String eGender;
	private String eStatus;
	private String regDate;
	private String eImgname;
	private String eHashtag;
	private String eContent;
	
	private String searchType;      	//검색타입(1:이름, 2:제묵, 3:내용)
	private String searchValue;      	//검색값
	private long startRow;         		//시작 rownum
	private long endRow;         		//끝rownum
	
	public WDExpert()
	{
		eId = "";
		eCode = "";
		ePwd = "";
		eName = "";
		eEmail = "";
		eLocation = "";
		eGender = "";
		eStatus = "";
		regDate = "";
		eImgname = "";
		eHashtag = "";
		eContent = "";
		
		searchType = "";
		searchValue = "";
		startRow = 0;
		endRow = 0;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String geteCode() {
		return eCode;
	}

	public void seteCode(String eCode) {
		this.eCode = eCode;
	}

	public String getePwd() {
		return ePwd;
	}

	public void setePwd(String ePwd) {
		this.ePwd = ePwd;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteEmail() {
		return eEmail;
	}

	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}

	public String geteLocation() {
		return eLocation;
	}

	public void seteLocation(String eLocation) {
		this.eLocation = eLocation;
	}

	public String geteGender() {
		return eGender;
	}

	public void seteGender(String eGender) {
		this.eGender = eGender;
	}

	public String geteStatus() {
		return eStatus;
	}

	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String geteImgname() {
		return eImgname;
	}

	public void seteImgname(String eImgname) {
		this.eImgname = eImgname;
	}

	public String geteHashtag() {
		return eHashtag;
	}

	public void seteHashtag(String eHashtag) {
		this.eHashtag = eHashtag;
	}

	public String geteContent() {
		return eContent;
	}

	public void seteContent(String eContent) {
		this.eContent = eContent;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getEndRow() {
		return endRow;
	}

	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}
	
	
}
