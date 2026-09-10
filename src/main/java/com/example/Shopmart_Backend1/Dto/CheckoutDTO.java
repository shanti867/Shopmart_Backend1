package com.example.Shopmart_Backend1.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class CheckoutDTO {
    private String user;
    private Object deliveryAddress;
    private String orderStatus;
    private String paymentMode;
    private String paymentStatus;
    private double subtotal;
    private double shipping;
    private double total;
    private Date date;
    private Object products;


}
