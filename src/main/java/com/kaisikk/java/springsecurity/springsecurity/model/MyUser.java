package com.kaisikk.java.springsecurity.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Kaisikk
 *
 * Сущность для хранения данных юзеров и использования их в security
 */
@Data
@Entity
@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String password;

    private String roles;

}
