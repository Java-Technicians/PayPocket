package com.alkemy.paypocket.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idcliente;

    @Column(name="rolName")
    private String name;

    @Column(name="rolDesc")
    private String descripcion;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "updateDate")
    private Timestamp updateDate;


}
