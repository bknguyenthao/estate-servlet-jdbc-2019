package com.laptrinhjavaweb.dto;

import java.sql.Timestamp;

public class BaseDTO<T> {
	private Long id;
	private String createdBy;
	private String modifiedBy;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
//	private int maxPageItem = 10;
//	private int currentPage = 1;
//	private String action;
//	private List<T> listResult = new ArrayList<T>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

//	public int getMaxPageItem() {
//		return maxPageItem;
//	}
//
//	public void setMaxPageItem(int maxPageItem) {
//		this.maxPageItem = maxPageItem;
//	}
//
//	public int getCurrentPage() {
//		return currentPage;
//	}
//
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//
//	public String getAction() {
//		return action;
//	}
//
//	public void setAction(String action) {
//		this.action = action;
//	}
//
//	public List<T> getListResult() {
//		return listResult;
//	}
//
//	public void setListResult(List<T> listResult) {
//		this.listResult = listResult;
//	}

}
