package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findByUser(String user);
}
