package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Dto.CheckoutDTO;
import com.example.Shopmart_Backend1.Entity.Checkout;
import com.example.Shopmart_Backend1.Service.CheckoutService;
import com.example.Shopmart_Backend1.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("*")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Checkout createCheckout(@RequestBody CheckoutDTO data, @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return checkoutService.createCheckout(data, username);
    }

    @GetMapping
    public List<Checkout> getAllCheckout(){
        return checkoutService.getAllCheckout();
    }

    @PutMapping("/{id}")
    public Checkout updateCheckout(@PathVariable Long id, @RequestBody CheckoutDTO data){
        return checkoutService.updateCheckout(id,data);
    }
    @GetMapping("/user/{user}")
    public List<Checkout> getUserCheckout(@PathVariable String user){
        return checkoutService.getUserCheckout(user);
    }
    @GetMapping("/{id}")
    public Optional<Checkout> getCheckout(@PathVariable Long id){
        return checkoutService.getCheckout(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCheckout(@PathVariable Long id){
        checkoutService.deleteCheckout(id);
    }

}
