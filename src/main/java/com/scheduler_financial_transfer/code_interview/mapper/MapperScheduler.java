package com.scheduler_financial_transfer.code_interview.mapper;

import com.scheduler_financial_transfer.code_interview.controller.request.SchedulerFinancialTransferRequestDTO;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import org.springframework.stereotype.Component;

@Component
public class MapperScheduler {

    public static ScheduleFinancialTransfer toSchedulerModel(SchedulerFinancialTransferRequestDTO dto){
        return ScheduleFinancialTransfer
                .builder()
                .id(dto.getId())
                .originAccountId(dto.getOriginAccountId())
                .destinationAccountId(dto.getDestinationAccountId())
                .transferValue(dto.getTransferValue())
                .status(dto.getStatus())
                .dateSchedule(dto.getDateSchedule())
                .dateTransfer(dto.getDateTransfer())
                .build();
    }

    public static SchedulerFinancialTransferRequestDTO toSchedulerDTO(ScheduleFinancialTransfer scheduler){
        return SchedulerFinancialTransferRequestDTO
                .builder()
                .id(scheduler.getId())
                .originAccountId(scheduler.getOriginAccountId())
                .destinationAccountId(scheduler.getDestinationAccountId())
                .transferValue(scheduler.getTransferValue())
                .status(scheduler.getStatus())
                .dateSchedule(scheduler.getDateSchedule())
                .dateTransfer(scheduler.getDateTransfer())
                .build();
    }

    public static SchedulerFinancialTransferRequestDTO toSchedulePresentationDTO(ScheduleFinancialTransfer scheduler){
        return SchedulerFinancialTransferRequestDTO
                .builder()
                .transferValue(scheduler.getTransferValue())
                .status(scheduler.getStatus())
                .dateSchedule(scheduler.getDateSchedule())
                .dateTransfer(scheduler.getDateTransfer())
                .build();
    }

}
