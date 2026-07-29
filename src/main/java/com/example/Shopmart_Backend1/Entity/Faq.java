package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String faqId;
    private String question;

    @Lob
    private String answer;
    private Boolean status;

}