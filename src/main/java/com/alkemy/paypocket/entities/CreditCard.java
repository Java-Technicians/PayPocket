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
@Schema(description = "Entidad Credit Card")
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount_available")
    private Double amountAvailable;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "soft_delete")
    private Boolean softDelete;


}
