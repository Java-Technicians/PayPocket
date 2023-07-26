package com.alkemy.paypocket.entities;

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
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount_available", nullable = false)
    private Double amountAvailable;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "soft_delete")
    private Boolean softDelete;


}
