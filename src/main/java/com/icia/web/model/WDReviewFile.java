package com.icia.web.model;

import java.io.Serializable;

public class WDReviewFile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long rSeq;
	private long rFileSeq;
	private String rFileName;
	private String rFileOrgName;
	private String rFileExt;
	private long rFileSize;
	private String regDate;
	
	public WDReviewFile() {
		  rSeq = 0;
		  rFileSeq = 0;
		  rFileName = "";
		  rFileOrgName = "";
		  rFileExt = "";
		  rFileSize = 0;
		  regDate = "";
	}

	public long getrSeq() {
		return rSeq;
	}

	public void setrSeq(long rSeq) {
		this.rSeq = rSeq;
	}

	public long getrFileSeq() {
		return rFileSeq;
	}

	public void setrFileSeq(long rFileSeq) {
		this.rFileSeq = rFileSeq;
	}

	public String getrFileName() {
		return rFileName;
	}

	public void setrFileName(String rFileName) {
		this.rFileName = rFileName;
	}

	public String getrFileOrgName() {
		return rFileOrgName;
	}

	public void setrFileOrgName(String rFileOrgName) {
		this.rFileOrgName = rFileOrgName;
	}

	public String getrFileExt() {
		return rFileExt;
	}

	public void setrFileExt(String rFileExt) {
		this.rFileExt = rFileExt;
	}

	public long getrFileSize() {
		return rFileSize;
	}

	public void setrFileSize(long rFileSize) {
		this.rFileSize = rFileSize;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
