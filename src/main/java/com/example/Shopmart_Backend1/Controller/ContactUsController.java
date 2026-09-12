package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.ContactUs;
import com.example.Shopmart_Backend1.Service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactus")
@CrossOrigin("*")
public class ContactUsController {
    @Autowired
    private ContactUsService contactUsService;

    @PostMapping
    public ContactUs create(@RequestBody ContactUs contactUs){
        return contactUsService.createContactUs(contactUs);
    }
    @GetMapping
    public List<ContactUs> getAll(){
        return contactUsService.getAll();
    }
}
