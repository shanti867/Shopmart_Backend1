package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByStatusTrue();
    Brand findByName(String name);
}