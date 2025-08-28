package com.scheduler_financial_transfer.code_interview.utils;

import org.springframework.stereotype.Component;

@Component
public class MessageConstants {
    public static final String INTERNAL_SERVER_ERROR = "internal-server-error";
    public static final String INVALID_ACCESS_TOKEN = "Invalid or expired access token to make request.";
    public static final String REQUEST_INVALID = "Request body is invalid.";
    public static final String FIELD_INVALID = "Field(s) are/is invalid('s) or null. Please check the documentation. \nErrorMessage {0}";
    public static final String FIELD_NULL_OR_EMPTY = "field-null-or-empty";
    public static final String DATE_INVALID_FORMAT = "invalid-date-format";
    public static final String SCHEDULE_DATE_IN_PAST = "schedule-date-in-past";
    public static final String TRANSFER_VALUE_NEGATIVE = "transfer-value-negative";
    public static final String ORIGIN_ACCOUNT_INVALIDATED = "origin-account-invalidated";
    public static final String DESTINATION_ACCOUNT_INVALIDATED = "destination-account-invalidated";

}