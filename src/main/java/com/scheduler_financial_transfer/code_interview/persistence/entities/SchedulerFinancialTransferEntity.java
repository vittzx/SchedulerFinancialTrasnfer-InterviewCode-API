package com.scheduler_financial_transfer.code_interview.persistence.entities;

import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SCHEDULER_TRANSFER")
@Builder
@Getter
public class SchedulerFinancialTransferEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "ORIGIN_ACCOUNT_ID", nullable = false)
    private String originAccountId;
    @Column(name = "DESTINATION_ACCOUNT_ID", nullable = false)
    private String destinationAccountId;
    @Column(name = "TRANSFER_VALUE", nullable = false)
    private Double transferValue;
    @Column(name = "DH_TRANSFER", nullable = false)
    private String dateTransfer;

    @Column(name = "DT_SCHEDULER", nullable = false)

    private String dateSchedule;

    @PrePersist
    private void prePersist(){
        this.dateTransfer = LocalDateTime.now().toString();
    }

}
