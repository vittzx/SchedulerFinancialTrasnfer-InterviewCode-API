package com.scheduler_financial_transfer.code_interview.services.validations;

import com.scheduler_financial_transfer.code_interview.exceptions.BadRequestException;
import com.scheduler_financial_transfer.code_interview.exceptions.ExceptionResponse;
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

import static com.scheduler_financial_transfer.code_interview.utils.MessageConstants.FIELD_NULL_OR_EMPTY;
import static com.scheduler_financial_transfer.code_interview.utils.MessageConstants.DATE_INVALID_FORMAT;


@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerValidationCreationFieldsImpl implements SchedulerValidationCreationFields {

    @Override
    public void validate(ScheduleFinancialTransfer schedule) {

        List<String> errors = new ArrayList<>();

        if(schedule.getOriginAccountId() == null || schedule.getOriginAccountId().isEmpty())
            errors.add("originAccountId");

        if(schedule.getDestinationAccountId() == null || schedule.getDestinationAccountId().isEmpty())
            errors.add("destinationAccountId");

        if(schedule.getDateSchedule() == null)
            errors.add("dateSchedule");

        if(schedule.getTransferValue() == null){
            errors.add("transferValue");
        }

        validateScheduleDate(schedule.getDateSchedule());
        checkErrorList(errors);

    }

    private void checkErrorList(List<String> errorList){
        if(!errorList.isEmpty()){
            log.error("Fields empty: {}", errorList);
            throw new BadRequestException(
                    new ExceptionResponse( FIELD_NULL_OR_EMPTY,
                            errorList.
                                    stream().collect(Collectors.joining(", "))
                    )
            );

        }
    }

    private void validateScheduleDate(String dateSchedule){
        try {
            if(dateSchedule == null) return;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dateSchedule, formatter);
        }
        catch (Exception e) {
            log.error("Invalid date");
            throw new BadRequestException(
                    new ExceptionResponse( DATE_INVALID_FORMAT,
                            dateSchedule
                    )
            );
        }
    }
}
