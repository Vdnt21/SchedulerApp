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
@Table( name = "clients")
public class Client {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "client_id" )
    private Integer id;

    @Column( name= "client_name", nullable = false )
    private String clientName;

    @Column( name = "client_email", nullable = false, unique = true )
    private String clientEmail;

    @Column( name = "client_password", nullable = false)
    private String password;

    @Column( name = "client_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
