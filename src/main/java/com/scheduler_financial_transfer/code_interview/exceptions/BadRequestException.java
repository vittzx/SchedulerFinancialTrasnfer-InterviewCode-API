package com.scheduler_financial_transfer.code_interview.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

    private final HttpStatus status;
    private final ExceptionResponse exception;

    public BadRequestException(ExceptionResponse ex){
        super(ex.getMessage());
        this.exception = ex;
        this.status = HttpStatus.BAD_REQUEST;
    }


}
