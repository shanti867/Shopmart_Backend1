package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Faq;
import com.example.Shopmart_Backend1.Entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByStatusTrue();
}