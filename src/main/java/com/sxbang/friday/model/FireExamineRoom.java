package com.sxbang.friday.model;


import com.sxbang.friday.model.BaseEntity;

public class FireExamineRoom {

	private Integer examineRoomId;
	private String examineRoomName;
	private String detailAddress;
	private Integer storage;

	public Integer getExamineRoomId() {
		return examineRoomId;
	}
	public void setExamineRoomId(Integer examineRoomId) {
		this.examineRoomId = examineRoomId;
	}
	public String getExamineRoomName() {
		return examineRoomName;
	}
	public void setExamineRoomName(String examineRoomName) {
		this.examineRoomName = examineRoomName;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public Integer getStorage(){return storage;}
	public void setStorage(int storage){this.storage=storage;}
}
