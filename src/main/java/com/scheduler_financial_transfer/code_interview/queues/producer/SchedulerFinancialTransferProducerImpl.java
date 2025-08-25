package com.scheduler_financial_transfer.code_interview.queues.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.queues.producer.use_case.SchedulerFinancialTransferProducerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerFinancialTransferProducerImpl implements SchedulerFinancialTransferProducerUseCase {
    private AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${rabbit-mq.scheduler-new.exchange}")
    private String SCHEDULER_NEW_FINANCIAL_TRANSFER_EXCHANGE;
    @Value("${rabbit-mq.scheduler-new.routing-key}")
    private String SCHEDULER_NEW_FINANCIAL_TRANSFER_ROUTING_KEY;


    public void sendNewScheduleMessage(ScheduleFinancialTransfer schedule) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(schedule);
        sendMessage(
                SCHEDULER_NEW_FINANCIAL_TRANSFER_EXCHANGE,
                SCHEDULER_NEW_FINANCIAL_TRANSFER_ROUTING_KEY,
                payload
        );
    }

    private void sendMessage(String exchangeName, String routingKey, String payload){
        log.info("Sending new message to: {} ---- message: {}", exchangeName, payload);
        amqpTemplate.convertAndSend(
            exchangeName, routingKey, payload
        );
    }

}
