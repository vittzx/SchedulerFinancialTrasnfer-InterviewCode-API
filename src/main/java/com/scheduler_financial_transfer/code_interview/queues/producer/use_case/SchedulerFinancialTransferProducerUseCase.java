package com.scheduler_financial_transfer.code_interview.queues.producer.use_case;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;

public interface SchedulerFinancialTransferProducerUseCase {
    void sendNewScheduleMessage(ScheduleFinancialTransfer schedule) throws JsonProcessingException;
}
