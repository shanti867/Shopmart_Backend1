package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Faq;
import com.example.Shopmart_Backend1.Entity.Feature;
import com.example.Shopmart_Backend1.Service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@CrossOrigin("*")
public class FaqController {
    @Autowired
    private FaqService service;

    @PostMapping
    public Faq createFaq(@RequestBody Faq faq)throws Exception{
        return service.save(faq);
    }

    @GetMapping
    public List<Faq> getAll(){
        return service.getAll();
    }

    @GetMapping("/active")
    public List<Faq> getActiveFaq(){
        return service.getActive();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public Faq updateFaq(@PathVariable Long id, @RequestBody Faq faq)throws Exception{
        return service.update(id, faq);
    }
}