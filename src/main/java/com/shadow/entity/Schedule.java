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
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "client_id", nullable = false)
//    private Client client;

    @Column(name = "api_url", nullable = false, columnDefinition = "TEXT")
    private String apiUrl;

    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();;

    @Column(name = "payload")
    private String payload;

    @Column(name = "headers")
    private String headers;

    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'ACTIVE'")
    private String status = "ACTIVE";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
