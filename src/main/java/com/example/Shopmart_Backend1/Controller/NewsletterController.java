package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Newsletter;
import com.example.Shopmart_Backend1.Service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PutMapping("/{id}/status")
    public Newsletter updateNewsletterStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request){

        boolean status = request.get("status");
        return newsletterService.updateNewsletterStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        newsletterService.deleteNewsletter(id);
    }
}
