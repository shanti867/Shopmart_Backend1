package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String featureId;
    private String name;
    private String icon;
    private String shortDescription;
    private Boolean status;
}