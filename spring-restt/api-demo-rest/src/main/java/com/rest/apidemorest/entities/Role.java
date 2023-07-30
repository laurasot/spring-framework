package com.rest.apidemorest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Role(String name){
        super();
        this.name = name;
    }
}
