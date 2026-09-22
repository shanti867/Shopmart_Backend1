package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Testimonial;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.TestimonialRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private UserRepository userRepository;

    public Testimonial create(Testimonial data, String username){
        User user = userRepository.findByUsernameIgnoreCase(username).orElse(null);

        if(user == null){
            return null;
        }
        data.setUser(user.getName());
        data.setUsername(user.getUsername());

        data.setStatus(true);

        Testimonial savedTestimonial = testimonialRepository.save(data);
        savedTestimonial.setTestimonialId("TES" + String.format("%03d", savedTestimonial.getId()));
        return testimonialRepository.save(savedTestimonial);
    }
    public List<Testimonial> getAll(){
        return testimonialRepository.findAll();
    }
    public List<Testimonial> getActive(){
        return testimonialRepository.findByStatusTrue();
    }
    public Testimonial updateTestimonial(Long id, Testimonial data, String username){
        Testimonial oldData  = testimonialRepository.findById(id).orElseThrow(()->new RuntimeException("Testimonial not found"));
        User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(()->new RuntimeException("User not found"));

        if(!oldData.getUser().equalsIgnoreCase(user.getName())){
            throw new RuntimeException("You are not allowed to update this review");
        }
        oldData.setMessage(data.getMessage());
        oldData.setStar(data.getStar());

        return testimonialRepository.save(oldData);
    }
}
