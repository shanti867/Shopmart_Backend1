package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Brand;
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
        Newsletter savedNewsletter = newsletterRepository.save(newsletter);
        savedNewsletter.setNewsletterId("NW"+String.format("%03d", savedNewsletter.getId()));
        return newsletterRepository.save(savedNewsletter);

    }
    public List<Newsletter> getNewsletter(){
        return newsletterRepository.findAll();
    }

    public Newsletter getNewsletterById(Long id){
        return newsletterRepository.findById(id).orElseThrow(()-> new RuntimeException("Newsletter Not Found") );
    }
    public Newsletter updateNewsletterStatus(Long id, boolean status){

        Newsletter newsletter = newsletterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Newsletter Not Found"));

        newsletter.setStatus(status);

        return newsletterRepository.save(newsletter);
    }
    public void deleteNewsletter(Long id){

        newsletterRepository.deleteById(id);
    }

}
