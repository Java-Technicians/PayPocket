package com.alkemy.paypocket.entities;

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

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id" )
    private Integer id;


    @Column(name = "currency", length = 100, nullable = false)
    private String currency;

    
    @Column(name = "transactionLimit", nullable = false)
    private double transactionLimit;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "creationDate")
    private double creationDate;

    @Column(name = "updateDate")
    private double updateDate;

    @Column(name = "softDelete")
    private double softDelete;

    @ManyToOne 
    @JoinColumn(name = "user_id")
    private User user_id;


}





