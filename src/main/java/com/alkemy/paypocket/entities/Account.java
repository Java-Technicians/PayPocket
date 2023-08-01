package com.alkemy.paypocket.entities;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id" )
    private Integer id_account;

    @Column(name = "currency", length = 100, nullable = false)
    private String currency;
    
    @Column(name = "transactionLimit", nullable = false)
    private double transactionLimit;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Column(name = "updateDate")
    private LocalDate updateDate;

    @Column(name = "softDelete")
    private boolean softDelete;

    @ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;


}





