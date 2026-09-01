package com.example.Shopmart_Backend1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String pin;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name ="user_id")
    @JsonIgnore
    private User user;
}
