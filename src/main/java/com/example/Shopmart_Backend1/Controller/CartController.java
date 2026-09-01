package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Cart;
import com.example.Shopmart_Backend1.Service.CartService;
import com.example.Shopmart_Backend1.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/cart")
    public Map<String,Object> createCart(
            @RequestHeader("Authorization") String authorization,
            @RequestBody Cart cart){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return cartService.createCart(username, cart);
    }

    @GetMapping("/cart")
    public Map<String,Object> getCart(
            @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return cartService.getCart(username);
    }

    @DeleteMapping("/cart/{id}")
    public Map<String,Object> deleteCart(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return cartService.deleteCart(username,id);
    }

}
