package com.scheduler_financial_transfer.code_interview.services.validations;

import com.scheduler_financial_transfer.code_interview.exceptions.BusinessRulesException;
import com.scheduler_financial_transfer.code_interview.exceptions.ExceptionResponse;
import com.scheduler_financial_transfer.code_interview.external_services.use_cases.ValidateAccountService;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.services.validations.use_cases.SchedulerBusinessRulesValidation;
import com.scheduler_financial_transfer.code_interview.utils.MessageConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessRulesValidationImpl implements SchedulerBusinessRulesValidation {

    private final ValidateAccountService validateAccountService;
    @Override
    public void validate(ScheduleFinancialTransfer schedule) {
        validateTransferValue(schedule.getTransferValue());
        validateScheduleDate(schedule.getDateSchedule());
        validateAccountsStatus(schedule.getOriginAccountId(), schedule.getDestinationAccountId());

        log.info("Schedule validated: {}",schedule);
    }

    private void validateTransferValue(Double transferValue) {
        if(transferValue < 0)
            throw new BusinessRulesException(
                    new ExceptionResponse(
                            MessageConstants.TRANSFER_VALUE_NEGATIVE
                    )
            );
    }

    private void validateScheduleDate(String dateSchedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTransferLocalDate = LocalDate.parse(dateSchedule, formatter);

        if(dateTransferLocalDate.isBefore(LocalDate.now())){
            throw new BusinessRulesException(
                    new ExceptionResponse(
                            MessageConstants.SCHEDULE_DATE_IN_PAST
                            ,dateTransferLocalDate
                    )
            );
        }
    }

    private void validateAccountsStatus(String originAccountId, String destinationAccountId) {
        if(isAccountIdInvalid(originAccountId)){
            throw new BusinessRulesException(
                    new ExceptionResponse(MessageConstants.ORIGIN_ACCOUNT_INVALIDATED)
            );
        }

        if(isAccountIdInvalid(destinationAccountId)){
            throw new BusinessRulesException(
                    new ExceptionResponse(MessageConstants.DESTINATION_ACCOUNT_INVALIDATED)
            );
        }
    }

    private Boolean isAccountIdInvalid(String accountId){
        return !(validateAccountService.validateAccountById(accountId));
    }
}
