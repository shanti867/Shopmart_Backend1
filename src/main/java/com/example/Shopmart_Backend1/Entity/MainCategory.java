package com.example.Shopmart_Backend1.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String mainCategoryId;
    private String name;
    private String pic;
    private Boolean status;

    // Getters and Setters
}