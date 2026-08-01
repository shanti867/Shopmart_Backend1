package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByStatusTrue();
}