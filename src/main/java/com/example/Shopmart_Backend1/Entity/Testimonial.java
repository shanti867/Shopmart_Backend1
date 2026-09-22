package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testimonialId;
    private String user;
    private String username;
    private String product;
    private String productName;
    private String message;
    private int star;
    private boolean status;
}
