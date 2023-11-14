package com.example.demo.common;

public class BusinessException extends RuntimeException {

    ResultStatus status;

    public BusinessException(ResultStatus status) {
        //不生成栈追踪信息
        super(status.getMessage(), null, false, false);
        this.status = status;
    }}
