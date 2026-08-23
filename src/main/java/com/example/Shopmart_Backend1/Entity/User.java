package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true, nullable=false)
    private String username;

    private String phone;

    @Column(unique = true, nullable=false)
    private String email;

    private String password;

    @Transient
    private String cpassword;
    private String role;
    private boolean status = true;
    private int failedLoginAttempts = 0;

}
