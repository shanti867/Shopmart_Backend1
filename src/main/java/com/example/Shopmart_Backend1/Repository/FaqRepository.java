package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}