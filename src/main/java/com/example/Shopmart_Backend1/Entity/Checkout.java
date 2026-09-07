package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user;

    @Column(columnDefinition="LONGTEXT")
    private String deliveryAddress;

    private String orderStatus;
    private String paymentMode;
    private String paymentStatus;
    private double subtotal;
    private double total;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(columnDefinition = "LONGTEXT")
    private String products;


}
