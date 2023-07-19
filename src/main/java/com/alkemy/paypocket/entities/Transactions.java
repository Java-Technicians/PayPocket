package com.alkemy.paypocket.entities;

import java.time.LocalDate;

import aj.org.objectweb.asm.Type;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")

public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account_id;

    @Column(name = "transactionDate")
    private LocalDate transactionDate;

}

/**
 * COMO desarrollador
 * QUIERO agregar la entidad Transaction
 * PARA representar en la implementación la estructura de datos
 * 
 * Criterios de aceptación:
 * Nombre de tabla: transactions.
 * Los campos son:
 * 
 * amount: DOUBLE NOT NULL
 * 
 * type: VARCHAR NOT NULL
 * 
 * description: VARCHAR NULLABLE
 * 
 * accountId: Clave foranea hacia ID de Account
 * 
 * timestamp (transactionDate)
 * 
 * Ideal que el campo type sea un Enum donde estén definidos los tipos de
 * transacciones, que serán income, payment, deposit
 * 
 * 
 */

