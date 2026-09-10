package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    Optional<NewsletterRepository> findByEmailIgnoreCase(String email);
}
