package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByStatusTrue();
}