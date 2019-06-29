package com.learn.account.model.dto;

import java.util.List;

public class PageDTO <T> {
	
	private int pageIndex;
	private int pageSize;
	private int totalPages;
	private Long totalElements;
	private List<T> content;
	
	public PageDTO setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}
	
	public PageDTO setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	
	public PageDTO setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		return this;
	}
	
	public PageDTO setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
		return this;
	}
	
	public PageDTO setContent(List<T> content) {
		this.content = content;
		return this;
	}
	
	
	public int getPageIndex() {
		return pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public List<T> getContent() {
		return content;
	}
	
	
	


}
