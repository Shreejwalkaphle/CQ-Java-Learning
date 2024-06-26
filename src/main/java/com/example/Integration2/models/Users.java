package com.example.Integration2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uname")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}
