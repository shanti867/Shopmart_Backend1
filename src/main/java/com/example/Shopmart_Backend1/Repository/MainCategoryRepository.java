package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findByStatusTrue();
    MainCategory findByName(String name);
}