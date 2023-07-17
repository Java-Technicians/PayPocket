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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id", length = 100, nullable = false)
    private int role_id;

    @Column(name="role_name", length = 100, nullable = false)
    private String name;

    @Column(name="role_desc", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "creationDate", length = 100, nullable = false)
    private Timestamp creationDate;

    @Column(name = "updateDate", length = 100, nullable = false)
    private Timestamp updateDate;


    public Role(String n, String d){
        name = n;
        descripcion = d;
    }


}
