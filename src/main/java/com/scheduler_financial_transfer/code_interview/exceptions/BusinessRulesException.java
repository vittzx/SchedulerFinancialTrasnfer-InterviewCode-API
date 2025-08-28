package com.scheduler_financial_transfer.code_interview.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessRulesException extends RuntimeException {

    private final HttpStatus status;
    private final ExceptionResponse exception;

    public BusinessRulesException(ExceptionResponse ex){
        super(ex.getMessage());
        this.exception = ex;
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}