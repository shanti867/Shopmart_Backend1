package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Feature;
import com.example.Shopmart_Backend1.Repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository repository;

    public Feature save(Feature feature)throws Exception{
        Feature savedFeature = repository.save(feature);
        savedFeature.setFeatureId("FTR" + String.format("%3d", savedFeature.getId()));
        return repository.save(savedFeature);
    }

    public List<Feature> getAll(){

        return repository.findAll();
    }
    public void delete(Long id){

        repository.deleteById(id);
    }
    public Feature update(Long id, Feature feature)throws Exception{
        Feature oldFeature = repository.findById(id).orElseThrow();
        if (feature.getName() != null && !feature.getName().trim().isEmpty()) {
            oldFeature.setName(feature.getName());
        }

        if (feature.getIcon() != null && !feature.getIcon().trim().isEmpty()) {
            oldFeature.setIcon(feature.getIcon());
        }

        if (feature.getShortDescription() != null && !feature.getShortDescription().trim().isEmpty()) {
            oldFeature.setShortDescription(feature.getShortDescription());
        }

        if (feature.getStatus() != null) {
            oldFeature.setStatus(feature.getStatus());
        }

        return repository.save(oldFeature);
    }
    public List<Feature> getActive(){
        return repository.findByStatusTrue();
    }

}