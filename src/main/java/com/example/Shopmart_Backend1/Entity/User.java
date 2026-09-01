package com.example.Shopmart_Backend1.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Transient
    private String cpassword;
    private String role;
    private boolean status = true;
    private int failedLoginAttempts = 0;

    @OneToMany(mappedBy = "user")
    private List<Address> address = new ArrayList<>();
}
