package com.scheduler_financial_transfer.code_interview.queues.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.scheduler_financial_transfer.code_interview.model.scheduler.ScheduleFinancialTransfer;
import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import com.scheduler_financial_transfer.code_interview.persistence.SchedulerFinancialTransferHistoryRepository;
import com.scheduler_financial_transfer.code_interview.persistence.SchedulerFinancialTransferRepository;
import com.scheduler_financial_transfer.code_interview.persistence.entities.SchedulerFinancialTransferHistoryEntity;
import com.scheduler_financial_transfer.code_interview.queues.consumer.dto.SchedulerFinancialTransferConsumerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.scheduler_financial_transfer.code_interview.mapper.MapperScheduler.toScheduleEntity;
import static com.scheduler_financial_transfer.code_interview.mapper.MapperScheduler.toSchedulerModel;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulerFinancialTransferConsumerImpl {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SchedulerFinancialTransferRepository schedulerRepository;
    private final SchedulerFinancialTransferHistoryRepository schedulerHistoryRepository;

    @RabbitListener(queues = {"scheduler-new-financial-transfer-queue"})
    // ideial seria pegar dinamicamente pelo yml, mas não sei um metodo que o valor seja constante em tempo de execucao
    public void receiveNewScheduleMessage(@Payload Message message){
        log.info("Message receive from new schedule queue: " + message);
        // colocar um cache p/ evitar duplições


        persistScheduler(convertMessageToSchedule(message), SchedulerFinancialTransferStatus.PROCESSING, "Schedule persisted with successfully to financial transfer!");
        // send to other microsservices the message.
    }

    @RabbitListener(queues = {"scheduler-success-financial-transfer-queue"})
    public void receiveSuccessScheduleMessage(@Payload Message message){
        log.info("Message receive from success schedule queue: " + message);
        persistScheduler(convertMessageToSchedule(message), SchedulerFinancialTransferStatus.COMPLETED, "Schedule completed!");

    }


    @RabbitListener(queues = {"scheduler-fail-financial-transfer-queue"})
    public void receiveFailedScheduleMessage(@Payload Message message, Channel channel){
        log.info("Message receive from failed schedule queue: " + message);
        persistScheduler(convertMessageToSchedule(message), SchedulerFinancialTransferStatus.ERROR, "Scheduler failed!");
    }

    private ScheduleFinancialTransfer convertMessageToSchedule(Message message){
        try{
            SchedulerFinancialTransferConsumerDTO dto =
                    objectMapper.readValue(message.getPayload().toString(), SchedulerFinancialTransferConsumerDTO.class);

            return toSchedulerModel(dto);
        }catch (Exception e){
            log.error("Error to convert message to ScheduleFinancialTransfer.class: {} ", e.getMessage());
            throw new IllegalArgumentException("Error to convert message to ScheduleFinancialTransfer.class: {}");
        }
    }

    private ScheduleFinancialTransfer persistScheduler(ScheduleFinancialTransfer schedule, SchedulerFinancialTransferStatus status, String description){
        schedule.setStatus(status);
        ScheduleFinancialTransfer persisted = toSchedulerModel(schedulerRepository.save(toScheduleEntity(schedule)));
        log.info("Schedule saved: {}", persisted );
        schedulerHistoryRepository.save(
                SchedulerFinancialTransferHistoryEntity
                        .builder()
                        .scheduleTransferId(persisted.getId())
                        .description(description)
                        .status(persisted.getStatus().name())
                        .build()
        );

        return persisted;
    }

}
