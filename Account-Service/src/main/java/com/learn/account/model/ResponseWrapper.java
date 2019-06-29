package com.learn.account.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ResponseWrapper <T> {
	
	private int status;
	private T data;
	private String message;
	private Date timeStamp;
	
	public ResponseWrapper (T data, String message, HttpStatus status) {
		this.data = data;
		this.message = message;
		this.status = status.value();
		this.timeStamp = new Date();
	}
	
	public ResponseWrapper (String message, HttpStatus status) {
		this.message = message;
		this.status = status.value();
		this.timeStamp = new Date();
	}

	public void setStatus(HttpStatus status) {
		this.status = status.value();
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
	
	
	
}
