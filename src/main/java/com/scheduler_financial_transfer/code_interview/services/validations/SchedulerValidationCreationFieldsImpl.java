package com.scheduler_financial_transfer.code_interview.services.validations;

import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.services.validations.use_cases.SchedulerValidationCreationFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerValidationCreationFieldsImpl implements SchedulerValidationCreationFields {

    @Override
    public void validate(ScheduleFinancialTransfer schedule) {

        List<String> errors = new ArrayList<>();

        if(schedule.getOriginAccountId() == null || schedule.getOriginAccountId().isEmpty())
            errors.add("originAccountId is null or empty");

        if(schedule.getDestinationAccountId() == null || schedule.getDestinationAccountId().isEmpty())
            errors.add("destinationAccountId is null or empty");

        if(schedule.getDateSchedule() == null)
            errors.add("schedule date is null");

        if(schedule.getTransferValue() == null){
            errors.add("transfer value is null");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(schedule.getDateSchedule(), formatter);

        }catch (Exception e) {
            errors.add("schedule date format is invalid, please follow dd/MM/yyy format. Date passed:");
        }
        checkErrorList(errors);

    }

    private void checkErrorList(List<String> errorList){
        if(!errorList.isEmpty()){
            throw new IllegalArgumentException("Errors:  " + errorList.stream().collect(Collectors.joining(", ")));
        }
    }
}
