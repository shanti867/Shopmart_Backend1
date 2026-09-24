package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.PaymentOrderDTO;
import com.example.Shopmart_Backend1.Dto.PaymentVerificationDTO;
import com.example.Shopmart_Backend1.Entity.Checkout;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.CheckoutRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private  String keySecret;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> createOrder(PaymentOrderDTO data, String username){
        Map<String, Object> response = new HashMap<>();

        try{
            User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(()-> new RuntimeException("User not found"));
            Checkout checkout = checkoutRepository.findById(data.getCheckoutId()).orElseThrow(()->new RuntimeException("Checkout not found"));

            if(checkout.getUser() == null || !checkout.getUser().equals(user.getId())){
                throw new RuntimeException("You are not allowed to pay for this order");
            }
            double amount = checkout.getTotal();
            long amountInpaise = Math.round(amount * 100);

            RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);
            JSONObject options = new JSONObject();
            options.put("amount",amountInpaise);
            options.put("currency","INR");
            options.put("receipt","SHOPMART_"+checkout.getId());

            Order order = razorpayClient.orders.create(options);
            checkout.setRazorpayOrderId(order.get("id"));
            checkout.setPaymentMode("Online");
            checkout.setPaymentStatus("Pending");
            checkoutRepository.save(checkout);

            response.put("status", true);
            response.put("message", "Razorpay order created");
            response.put("key", keyId);
            response.put("razorpayOrderId", order.get("id"));
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));
            response.put("checkoutId",checkout.getId());

        }
        catch(Exception e){
            response.put("status",false);
            response.put("message",e.getMessage());
        }
        return response;
    }

    public Map<String, Object> verifyPayment(PaymentVerificationDTO data, String username){
        Map<String, Object> response = new HashMap<>();
        try{
            User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(()->new RuntimeException("User not found"));
            Checkout checkout = checkoutRepository.findById(data.getCheckoutId()).orElseThrow(()->new RuntimeException("Checkout not Found"));

            if ("Paid".equalsIgnoreCase(checkout.getPaymentStatus())) {
                throw new RuntimeException("This order is already paid");
            }
            if(checkout.getUser() == null || !checkout.getUser().equals(user.getId())){
                throw new RuntimeException("You are not allowed to update this order");
            }
            if(checkout.getRazorpayOrderId() == null || !checkout.getRazorpayOrderId().equals(data.getRazorpayOrderId())){
                throw new RuntimeException("Invalid Razorpay order");
            }
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", data.getRazorpayOrderId());
            options.put("razorpay_payment_id", data.getRazorpayPaymentId());
            options.put("razorpay_signature", data.getRazorpaySignature());

            boolean verified = Utils.verifyPaymentSignature(options,keySecret);

            if(!verified){
                checkout.setPaymentStatus("Failed");
                checkoutRepository.save(checkout);
                response.put("status", false);
                response.put("message", "Payment verification failed");
                return response;

            }
            checkout.setPaymentStatus("Paid");
            checkout.setPaymentMode("Online");
            checkout.setRazorpayPaymentId(data.getRazorpayPaymentId());
            checkoutRepository.save(checkout);
            response.put("status", true);
            response.put("message", "Payment successful");
            response.put("paymentId",data.getRazorpayPaymentId());
            response.put("orderId", data.getRazorpayOrderId());
            response.put("checkoutId", checkout.getId());
        }
        catch(Exception e){
            response.put("status",false);
            response.put("message",e.getMessage());
        }
        return response;
    }
}
