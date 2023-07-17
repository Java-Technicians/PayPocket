package com.alkemy.paypocket.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
    @Id

    private int id;

/*
    @Column(name="role_name")
    private String name;

    @Column(name="role_desc")
    private String descripcion;


    @Column(name = "creation_date")
   private LocalDate creationDate;

    @Column(name = "update_date")
    private LocalDate updateDate;


    public Role(String n, String d){
        name = n;
        descripcion = d;
    } */


}
