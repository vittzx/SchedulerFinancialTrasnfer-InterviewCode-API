package com.scheduler_financial_transfer.code_interview.services.validations;

import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.services.validations.use_cases.SchedulerBusinessRulesValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessRulesValidationImpl implements SchedulerBusinessRulesValidation {

    @Override
    public void validate(ScheduleFinancialTransfer schedule) {
        log.debug("Starting validating schedule: {}",schedule);






    }
}
