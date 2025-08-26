package com.scheduler_financial_transfer.code_interview.persistence.entities;

import com.scheduler_financial_transfer.code_interview.model.scheduler.SchedulerFinancialTransferStatus;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SCHEDULER_TRANSFER_HISTORY")
@Builder
public class SchedulerFinancialTransferHistoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SCHEDULER_TRANSFER_ID", nullable = false)
    private Long scheduleTransferId;
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DH_UPDATED", nullable = false)
    private String dhUpdated;

    @PrePersist
    private void prePersist(){
        this.dhUpdated = LocalDateTime.now().toString();
    }


}
