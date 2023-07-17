package com.alkemy.paypocket.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "updateDate")
    private Timestamp updateDate;

    @Column(name = "softDelete")
    private Boolean softDelete;
}
