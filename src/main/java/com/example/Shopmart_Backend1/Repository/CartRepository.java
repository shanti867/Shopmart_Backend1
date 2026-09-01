package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Cart;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserAndProduct(User user, Product product);
    List<Cart> findByUser(User user);
    Optional<Cart> findByIdAndUser(Long id, User user);

}
