package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Newsletter;
import com.example.Shopmart_Backend1.Repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterService {
    @Autowired
    private NewsletterRepository newsletterRepository;

    public Newsletter createNewsletter(Newsletter newsletter){
        if(newsletterRepository.findByEmailIgnoreCase(newsletter.getEmail()).isPresent()){
            throw new RuntimeException("This Email Address Has Already Registered");
        }
        newsletter.setStatus(true);
        return newsletterRepository.save(newsletter);
    }
    public List<Newsletter> getNewsletter(){
        return newsletterRepository.findAll();
    }
    public Newsletter getNewsletterById(Long id){
        return newsletterRepository.findById(id).orElseThrow(()-> new RuntimeException("Newsletter Not Found") );
    }

}
