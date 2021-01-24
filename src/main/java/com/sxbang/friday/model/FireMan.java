package com.sxbang.friday.model;


import com.sxbang.friday.model.BaseEntity;

public class FireMan  {

	private Integer examineUserid;
	private String examineUsername;
	private Integer orgId;
	private Integer levelId;
	private String level;
	private String telephone;
	private String identityNumber;
	private  String orgName;

	public Integer getExamineUserid() {
		return examineUserid;
	}
	public void setExamineUserid(Integer examineUserid) {
		this.examineUserid = examineUserid;
	}
	public String getExamineUsername() {
		return examineUsername;
	}
	public void setExamineUsername(String examineUsername) {
		this.examineUsername = examineUsername;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
