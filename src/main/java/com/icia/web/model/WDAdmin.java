package com.icia.web.model;

import java.io.Serializable;

public class WDAdmin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String adminId;
	private String admPwd;
	private String admName;
	private String status;
	private String regDate;
	
	public WDAdmin() {
		adminId = "";
		admPwd = "";
		admName = "";
		status = "";
		regDate = "";
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdmPwd() {
		return admPwd;
	}

	public void setAdmPwd(String admPwd) {
		this.admPwd = admPwd;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	

}
