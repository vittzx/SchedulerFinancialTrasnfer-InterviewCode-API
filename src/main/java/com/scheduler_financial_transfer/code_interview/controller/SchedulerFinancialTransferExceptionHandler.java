package com.scheduler_financial_transfer.code_interview.controller;




import com.scheduler_financial_transfer.code_interview.utils.MessageConstants;
import com.scheduler_financial_transfer.code_interview.exceptions.BadRequestException;
import com.scheduler_financial_transfer.code_interview.exceptions.BusinessRulesException;
import com.scheduler_financial_transfer.code_interview.exceptions.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
// @RestControllerAdvice(assignableTypes = SchedulerFinancialTransferController.class)
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class SchedulerFinancialTransferExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralException(final Exception ex){
        log.error("Handling Exception {}", ex.getLocalizedMessage());
        final ExceptionResponse exResponse = new ExceptionResponse(MessageConstants.INTERNAL_SERVER_ERROR);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exResponse);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex){
        log.error("Handling MethodArgumentTypeMismatchException {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, new ExceptionResponse(MessageFormat.format(MessageConstants.FIELD_INVALID , ex.getName())));
    }

    @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Object> handleHttpClientErrorUnauthorizedException(final HttpClientErrorException ex){
        log.error("Handling InvalidAccessTokenException {}", ex.getMessage());
        return createErrorResponse(HttpStatus.UNAUTHORIZED, new ExceptionResponse(MessageConstants.INVALID_ACCESS_TOKEN));
    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex){
        log.error("Handling BadRequestException {}", ex.getMessage());
        return createErrorResponse(ex.getStatus(), ex.getException());
    }

    @ExceptionHandler(value = BusinessRulesException.class)
    public ResponseEntity<Object> handleHttpClientBusinessRulesException(final BusinessRulesException ex){
        log.error("Handling BusinessRulesException {}", ex.getMessage());
        return createErrorResponse(ex.getStatus(), ex.getException());
    }


    private ResponseEntity<Object> createErrorResponse(HttpStatus status, ExceptionResponse body){
        return ResponseEntity.status(status).body(body);
    }

}