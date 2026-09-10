package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Newsletter;
import com.example.Shopmart_Backend1.Service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin("*")
public class NewsletterController{
    @Autowired
    private NewsletterService newsletterService;

    @PostMapping
    public Newsletter createNewsletter(@RequestBody Newsletter newsletter){
        return newsletterService.createNewsletter(newsletter);
    }

    @GetMapping
    public List<Newsletter> getNewsletter(){
        return newsletterService.getNewsletter();
    }
    @GetMapping("/{id}")
    public Newsletter getNewsletterById(@PathVariable Long id){
        return newsletterService.getNewsletterById(id);
    }
}
