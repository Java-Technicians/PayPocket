package com.alkemy.paypocket.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="rolName")
    private String name;

    @Column(name="rolDesc")
    private String descripcion;

    /*
    @Column(name = "creationDate")
   private Timestamp creationDate;

    @Column(name = "updateDate")
    private Timestamp updateDate;


    public Role(int i, String n, String d){
        id = i;
        name = n;
        descripcion = d;
    }

     */
}
