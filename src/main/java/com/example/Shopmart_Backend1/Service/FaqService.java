package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Faq;
import com.example.Shopmart_Backend1.Entity.Feature;
import com.example.Shopmart_Backend1.Repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {
    @Autowired
    private FaqRepository repository;

    public Faq save(Faq faq)throws Exception{
        Faq savedFaq = repository.save(faq);
        savedFaq.setFaqId("FTR" + String.format("%3d", savedFaq.getId()));

        return repository.save(savedFaq);
    }
    public List<Faq> getAll(){
        return repository.findAll();
    }
    public List<Faq> getActive(){
        return repository.findByStatusTrue();
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public Faq update(Long id, Faq faq)throws Exception{
        Faq oldFaq = repository.findById(id).orElseThrow();
        if(faq.getAnswer()!= null && !faq.getAnswer().trim().isEmpty()){
            oldFaq.setAnswer(faq.getAnswer());
        }
        if(faq.getQuestion() != null && !faq.getQuestion().trim().isEmpty()){
            oldFaq.setQuestion(faq.getQuestion());
        }
        if(faq.getStatus() != null){
            oldFaq.setStatus(faq.getStatus());
        }
        System.out.println("Question = " + faq.getQuestion());
        System.out.println("Question Length = " + faq.getQuestion().length());
        return repository.save(oldFaq);
    }
}