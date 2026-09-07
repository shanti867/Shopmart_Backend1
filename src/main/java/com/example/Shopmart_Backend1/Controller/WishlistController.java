package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Dto.WishlistDTO;
import com.example.Shopmart_Backend1.Service.JwtService;
import com.example.Shopmart_Backend1.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private JwtService jwtService;


    // CREATE
    @PostMapping("/wishlist")
    public Map<String, Object> createWishlist(
            @RequestHeader("Authorization") String authorization,
            @RequestBody WishlistDTO wishlistDTO) {

        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return wishlistService.createWishlist(
                username,
                wishlistDTO
        );
    }


    // GET
    @GetMapping("/wishlist")
    public Map<String, Object> getWishlist(
            @RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return wishlistService.getWishlist(username);
    }


    // DELETE
    @DeleteMapping("/wishlist/{id}")
    public Map<String, Object> deleteWishlist(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {

        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return wishlistService.deleteWishlist(
                username,
                id
        );
    }
}
