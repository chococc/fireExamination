package com.sxbang.friday.model;

import com.sxbang.friday.model.BaseEntity;

import java.math.BigDecimal;


public class FireLevel {

	private Integer levelId;
	private String level;
	private BigDecimal money;

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
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
