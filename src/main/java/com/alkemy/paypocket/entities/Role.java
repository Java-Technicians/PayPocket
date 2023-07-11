package com.alkemy.paypocket.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Role {
    @Id
    private int id;

    @Column(name="rolName")
    private String name;

    @Column(name="rolDesc")
    private String description;


}
