package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.MainCategory;
import com.example.Shopmart_Backend1.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByStatusTrue();
}
