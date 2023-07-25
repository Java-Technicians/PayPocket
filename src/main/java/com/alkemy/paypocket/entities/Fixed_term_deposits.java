package com.alkemy.paypocket.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fixed_term_deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ftd_id")
    private Integer id;

    @Column(name="ftd_amount", nullable = false)
    private Double amount;

    @Column(name="ftd_interest", nullable = false)
    private Double interest;

    @Column(name = "ftd_creation_date")
    private LocalDate creationDate;

    @Column(name = "ftd_closing_date")
    private LocalDate closingDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
