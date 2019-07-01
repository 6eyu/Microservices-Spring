package com.learn.oauth2.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ResponseWrapper<T> implements Serializable {

    private T data;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date timestamp;
    private String message;

    public ResponseWrapper() {
        this.timestamp = new Date();
    }

    public ResponseWrapper(int status) {
        this();
        this.status = status;
    }

    public ResponseWrapper(String message, int status) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

    public ResponseWrapper(T data, int status) {
        this.status = status;
        this.data = data;
        this.timestamp = new Date();
    }

    public ResponseWrapper(T data, int status, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }
}
