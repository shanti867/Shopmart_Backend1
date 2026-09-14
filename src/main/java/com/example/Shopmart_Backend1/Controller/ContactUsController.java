package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.ContactUs;
import com.example.Shopmart_Backend1.Entity.Newsletter;
import com.example.Shopmart_Backend1.Service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PutMapping("/{id}/status")
    public ContactUs updateContactUsStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request){

        boolean status = request.get("status");

        return contactUsService.updateContactUsStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        contactUsService.deleteContactUs(id);
    }
}


