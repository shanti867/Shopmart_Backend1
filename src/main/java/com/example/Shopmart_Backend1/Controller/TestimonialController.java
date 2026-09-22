package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Testimonial;
import com.example.Shopmart_Backend1.Service.JwtService;
import com.example.Shopmart_Backend1.Service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/testimonial")
@CrossOrigin("*")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Map<String,Object> create(@RequestBody Testimonial data, @RequestHeader("Authorization") String authorization){

        Map<String,Object> response = new HashMap<>();
        try{
            String token = authorization.substring(7);
            String username = jwtService.extractUsername(token);

            Testimonial result = testimonialService.create(data,username);
            if(result == null){
                response.put("status", false);
                response.put("message", "User not found");
            }
            else{
                response.put("status",true);
                response.put("message", "Review created successfully");
                response.put("data", result);
            }
        }
        catch(Exception e){
            response.put("status",false);
            response.put("message",e.getMessage());
        }
        return response;
    }

    @GetMapping
    public Map<String, Object> getAll(){
        Map<String, Object> response = new HashMap<>();
        response.put("status",true);
        response.put("data",testimonialService.getAll());
        return response;
    }

    @GetMapping("/active")
    public Map<String, Object> getActive(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("data", testimonialService.getActive());

        return response;
    }

    @PutMapping("/{id}")
    public Testimonial updateTestimonial(@PathVariable Long id, @RequestBody Testimonial data, @RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        String username = jwtService.extractUsername(token);

        return testimonialService.updateTestimonial(id, data, username);
    }
}
