package com.scheduler_financial_transfer.code_interview.external_services;

import com.scheduler_financial_transfer.code_interview.external_services.use_cases.ValidateAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//@FeignClient
@Slf4j
@Service
public class ValidateAccountClient implements ValidateAccountService {

    //@GetMapping
    @Override
    public Boolean validateAccountById(String accountId) {
        log.debug("Validating account id: {}", accountId);
        return true;
    }
}
