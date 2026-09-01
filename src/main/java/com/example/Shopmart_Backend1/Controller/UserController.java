package com.example.Shopmart_Backend1.Controller;


import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Service.JwtService;
import com.example.Shopmart_Backend1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAll(){

        return userService.getAll();
    }

    @PostMapping("/login")
    public Map<String,Object> loginUser(@RequestBody User user){

        return userService.loginUser(user);
    }
    @PutMapping()
    public Map<String,Object> updateProfile(@RequestHeader("authorization") String authorization, @RequestBody User user){
        if(authorization == null || !authorization.startsWith("Bearer ")){
            return Map.of(
                    "status",false,
                    "message", "authorization token is missing"
            );
        }
        String token = authorization.substring(7);
        try{
            String username = jwtService.extractUsername(token);
            return userService.updateProfile(username, user);
        }
        catch(Exception e){
            return Map.of(
                    "status", false,
                    "message", "invalid or Expired token"
            );
        }

    }

    @GetMapping("/profile")
    public Map<String,Object> getProfile(@RequestHeader("Authorization") String authorization){
        if(authorization == null || !authorization.startsWith("Bearer ")){
            return Map.of(
                    "status", false,
                    "message", "Authorization token is missing"
            );
        }
        String token = authorization.substring(7);
        try{
            String username = jwtService.extractUsername(token);
            return userService.getProfile(username);
        }
        catch(Exception e){
            return Map.of(
                    "status", false,
                    "message", "Invalid or Expire token"
            );
        }
    }
}
