package com.scheduler_financial_transfer.code_interview.queues.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SchedulerFinancialTransferConsumerDTO {

    private Long id;
    private String originAccountId;
    private String destinationAccountId;
    private Double transferValue;
    private String dateTransfer;
    private SchedulerFinancialTransferStatus status;
    private String dateSchedule;
}
