package com.example.Shopmart_Backend1.Dto;

import lombok.Data;

@Data
public class PaymentVerificationDTO {
    private Long checkoutId;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}
