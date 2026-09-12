package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ContactUs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String contactUsId;
    private String name;
    private String email;
    private String phone;
    private String subject;

    @Lob
    private String message;

}
