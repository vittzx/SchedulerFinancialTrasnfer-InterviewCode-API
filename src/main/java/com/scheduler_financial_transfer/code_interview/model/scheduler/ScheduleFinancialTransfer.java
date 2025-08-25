package com.scheduler_financial_transfer.code_interview.model.scheduler;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleFinancialTransfer {
    private Long id;
    private String originAccountId;
    private String destinationAccountId;
    private Double transferValue;
    private String dateTransfer;
    private SchedulerFinancialTransferStatus status;
    private String dateSchedule;
}
