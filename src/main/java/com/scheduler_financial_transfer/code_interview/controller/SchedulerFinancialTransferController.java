package com.scheduler_financial_transfer.code_interview.controller;

import com.scheduler_financial_transfer.code_interview.controller.request.SchedulerFinancialTransferRequestDTO;
import com.scheduler_financial_transfer.code_interview.services.use_cases.SchedulerFinancialTransferUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/schedule")
public class SchedulerFinancialTransferController {

    private final SchedulerFinancialTransferUseCase scheduleUseCase;
    @PostMapping
    public ResponseEntity<SchedulerFinancialTransferRequestDTO> scheduleTransfer(
            @RequestBody SchedulerFinancialTransferRequestDTO body,
            @RequestHeader(required = true) String originAccountId,
            @RequestHeader(required = true) String destinationAccountId,
            HttpServletRequest request
    ){
        body.setOriginAccountId(originAccountId);
        body.setDestinationAccountId(destinationAccountId);
        log.debug("POST - /v1/schedule body: {}", body);
        return ResponseEntity.ok(
                scheduleUseCase.schedule(body)
        );
    }

}
