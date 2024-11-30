package com.shadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table( name = "users7")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    @Column( nullable = false, unique = true )
    private String username;

    @Column( nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'USER'")
    private String role = "USER";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}