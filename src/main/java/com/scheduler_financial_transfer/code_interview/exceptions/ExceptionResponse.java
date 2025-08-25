package com.scheduler_financial_transfer.code_interview.exceptions;

import com.scheduler_financial_transfer.code_interview.config.MessageConfig;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {

    private String status;
    private String message;

    public ExceptionResponse(String status, Object... args){
        this.status = status;
        this.message = new MessageConfig().getMessage(status, args);
    }
}