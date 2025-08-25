package com.scheduler_financial_transfer.code_interview.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scheduler_financial_transfer.code_interview.controller.request.SchedulerFinancialTransferRequestDTO;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import com.scheduler_financial_transfer.code_interview.queues.producer.use_case.SchedulerFinancialTransferProducerUseCase;
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
    private final SchedulerFinancialTransferProducerUseCase sendNewSchedule;
    @Override
    public SchedulerFinancialTransferRequestDTO schedule(SchedulerFinancialTransferRequestDTO dto){
        log.info("Creating schedule {}", dto);

        ScheduleFinancialTransfer schedule = toSchedulerModel(dto);
        setInitialScheduleFields(schedule);

        creationFieldsValidation.validate(
                schedule
        );

        businessRulesValidation.validate(
                schedule
        );

        sendNewScheduleMessage(schedule);

        return toSchedulePresentationDTO(schedule);
    }

    private void setInitialScheduleFields(ScheduleFinancialTransfer schedule){
        schedule.setStatus(SchedulerFinancialTransferStatus.CREATED);
    }

    private void sendNewScheduleMessage(ScheduleFinancialTransfer schedule){

        try {
            sendNewSchedule.sendNewScheduleMessage(
                    schedule
            );
        }catch (Exception e){
            log.error("ERROR: Error trying to send new message about schedule: " + e.getMessage());
        }
    }
}