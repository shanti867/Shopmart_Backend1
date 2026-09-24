package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Dto.PaymentOrderDTO;
import com.example.Shopmart_Backend1.Dto.PaymentVerificationDTO;
import com.example.Shopmart_Backend1.Service.JwtService;
import com.example.Shopmart_Backend1.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create-order")
    public Map<String, Object> createOrder(@RequestBody PaymentOrderDTO data, @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return paymentService.createOrder(data,username);
    }

    @PostMapping("/verify")
    public Map<String, Object> verifyPayment(@RequestBody PaymentVerificationDTO data, @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return paymentService.verifyPayment(data, username);
    }

}
