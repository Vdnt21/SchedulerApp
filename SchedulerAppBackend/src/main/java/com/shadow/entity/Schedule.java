package com.shadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "schedule_id")
    private Integer id;

    @Column(name = "schedule_client", nullable = false)
    private String clientName;

    @Column(name = "schedule_api_url", nullable = false, columnDefinition = "TEXT")
    private String apiUrl;

    @Column(name = "schedule_cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "schedule_payload")
    private String payload;

    @Column(name = "schedule_headers")
    private String headers;

    @Column(name = "schedule_status", nullable = false, columnDefinition = "VARCHAR(10)")
    private String status = "ACTIVE";

    @Column(name = "schedule_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
