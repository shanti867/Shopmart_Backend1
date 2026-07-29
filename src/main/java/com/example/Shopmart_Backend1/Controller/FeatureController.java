package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Feature;
import com.example.Shopmart_Backend1.Service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    private FeatureService service;

    @PostMapping
    public Feature createFeature(@RequestBody Feature feature)throws Exception{
        return service.save(feature);
    }
    @GetMapping
    public List<Feature> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @PutMapping("/{id}")
    public Feature update(@PathVariable Long id, @RequestBody Feature feature)throws Exception{
        return service.update(id, feature);
    }
}