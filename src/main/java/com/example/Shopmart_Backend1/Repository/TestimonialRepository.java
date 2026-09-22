package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialRepository extends JpaRepository<Testimonial,Long> {
    List<Testimonial> findByStatusTrue();
}
