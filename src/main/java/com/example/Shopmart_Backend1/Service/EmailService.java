package com.example.Shopmart_Backend1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmationEmail(String customerEmail, Long checkoutId, double total){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject("Shopmart-Order Confirmation");
        message.setText(
                "Dear Customer,\n\n" +
                        "Thank you for shopping with Shopmart!\n\n" +
                        "Your order has been successfully placed.\n\n" +
                        "Order ID: " + checkoutId + "\n" +
                        "Order Total: ₹" + total + "\n\n" +
                        "Your order is now being processed. " +
                        "You can track your order from your account dashboard.\n\n" +
                        "We appreciate your trust and look forward to serving you again!\n\n" +
                        "Regards,\n" +
                        "Shopmart Team"
        );
        mailSender.send(message);

    }
}
