package com.rest.apidemorest.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @jakarta.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "birth_date")
    private Date birthDate;
    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;
}
