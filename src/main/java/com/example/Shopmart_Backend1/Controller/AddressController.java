package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Address;
import com.example.Shopmart_Backend1.Service.AddressService;
import com.example.Shopmart_Backend1.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/address")
    public Map<String,Object> addAddress(
            @RequestHeader("Authorization") String authorization, @RequestBody Address address){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return addressService.addAddress(username, address);
    }

    @DeleteMapping("/address/{id}")
    public Map<String,Object> deleteAddress(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return addressService.deleteAddress(username, id);
    }

    @GetMapping("/address")
    public Map<String,Object> getAddresses(
            @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return addressService.getAddress(username);
    }

    @PutMapping("/address/{id}")
    public Map<String,Object> updatedAddress(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestBody Address address){

        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);
        return addressService.updateAddress(
                username,
                id, address
        );
    }
}
