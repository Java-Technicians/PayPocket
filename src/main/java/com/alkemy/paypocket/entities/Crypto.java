package com.alkemy.paypocket.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "crypto")
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crypto_id" )
    private Integer id_crypto;


    @Column(name="name", nullable = false)
    private String name;

    @Column(name="amount", nullable = false)
    private double amount;

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
