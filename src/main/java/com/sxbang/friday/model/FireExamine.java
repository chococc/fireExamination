package com.sxbang.friday.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FireExamine  {

	private Integer examineId;
	private Integer levelId;
	private BigDecimal money;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date date;
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
	private Date startTime;
	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
	private Date endTime;
	private Integer number;
	private Integer orderNumber;
	private Integer examineRoomId;
	private String examineName;
	private Integer status;

	private  String level;

	private Integer firviewIdOne;
	private Integer firviewIdTwo;

	private String firviewIdOneName;
	private String firviewIdTwoName;

	public Integer getExamineId() {
		return examineId;
	}
	public void setExamineId(Integer examineId) {
		this.examineId = examineId;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getExamineRoomId() {
		return examineRoomId;
	}
	public void setExamineRoomId(Integer examineRoomId) {
		this.examineRoomId = examineRoomId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getFirviewIdOne() {
		return firviewIdOne;
	}

	public void setFirviewIdOne(Integer firviewIdOne) {
		this.firviewIdOne = firviewIdOne;
	}

	public Integer getFirviewIdTwo() {
		return firviewIdTwo;
	}

	public void setFirviewIdTwo(Integer firviewIdTwo) {
		this.firviewIdTwo = firviewIdTwo;
	}

	public String getFirviewIdOneName() {
		return firviewIdOneName;
	}

	public void setFirviewIdOneName(String firviewIdOneName) {
		this.firviewIdOneName = firviewIdOneName;
	}

	public String getFirviewIdTwoName() {
		return firviewIdTwoName;
	}

	public void setFirviewIdTwoName(String firviewIdTwoName) {
		this.firviewIdTwoName = firviewIdTwoName;
	}
}
