package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.ContactUs;
import com.example.Shopmart_Backend1.Entity.Newsletter;
import com.example.Shopmart_Backend1.Repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;
    public ContactUs createContactUs(ContactUs contactUs){
        ContactUs savedContactUs = contactUsRepository.save(contactUs);
        savedContactUs.setContactUsId("CT"+String.format("%03d",savedContactUs.getId()));
        return contactUsRepository.save(savedContactUs);
    }

    public List<ContactUs> getAll(){
        return contactUsRepository.findAll();
    }
    public ContactUs updateContactUsStatus(Long id, boolean status){

        ContactUs contactUs = contactUsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContactUs Not Found"));

        contactUs.setStatus(status);

        return contactUsRepository.save(contactUs);
    }
    public void deleteContactUs(Long id){
        contactUsRepository.deleteById(id);
    }
}
