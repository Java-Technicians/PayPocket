package com.alkemy.paypocket.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_Id")
    private Integer id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "transactionDate")
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
