package com.scheduler_financial_transfer.code_interview.services.validations.use_cases;

import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;

public interface SchedulerValidationCreationFields {
    void validate(ScheduleFinancialTransfer schedule);
}
