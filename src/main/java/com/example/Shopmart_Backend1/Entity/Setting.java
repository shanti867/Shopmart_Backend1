package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String map1;
    private String map2;
    private String siteName;
    private String email;
    private String phone;
    private String whatsapp;
    private String facebook;
    private String twitter;
    private String instagram;
    private String linkedin;
    private String youtube;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String privacyPolicy;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String termsAndConditions;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String refundPolicy;

}