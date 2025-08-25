package com.scheduler_financial_transfer.code_interview.services;

import com.scheduler_financial_transfer.code_interview.controller.request.SchedulerFinancialTransferRequestDTO;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import com.scheduler_financial_transfer.code_interview.services.validations.use_cases.SchedulerBusinessRulesValidation;
import com.scheduler_financial_transfer.code_interview.services.use_cases.SchedulerFinancialTransferUseCase;
import com.scheduler_financial_transfer.code_interview.services.validations.use_cases.SchedulerValidationCreationFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.scheduler_financial_transfer.code_interview.mapper.MapperScheduler.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class SchedulerFinancialTransferUseCaseImpl implements SchedulerFinancialTransferUseCase {

    private final SchedulerValidationCreationFields creationFieldsValidation;
    private final SchedulerBusinessRulesValidation businessRulesValidation;
    @Override
    public SchedulerFinancialTransferRequestDTO schedule(SchedulerFinancialTransferRequestDTO dto) {
        log.info("Creating schedule {}", dto);

        ScheduleFinancialTransfer schedule = toSchedulerModel(dto);
        setInitialScheduleFields(schedule);

        creationFieldsValidation.validate(
                schedule
        );

        businessRulesValidation.validate(
                schedule
        );

        return toSchedulePresentationDTO(schedule);
    }

    private void setInitialScheduleFields(ScheduleFinancialTransfer schedule){
        schedule.setStatus(SchedulerFinancialTransferStatus.CREATED);
    }
}