package com.scheduler_financial_transfer.code_interview.controller;

import com.scheduler_financial_transfer.code_interview.controller.request.SchedulerFinancialTransferRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/schedule")
public class SchedulerFinancialTransferController {


    @PostMapping
    public ResponseEntity<SchedulerFinancialTransferRequestDTO> scheduleTransfer(
            @RequestBody SchedulerFinancialTransferRequestDTO body,
            HttpServletRequest request
    ){

        // validating and send message
        return null;
    }

}
