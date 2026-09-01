package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserAndProduct(User user, Product product);
    List<Wishlist> findByUser(User user);
    Optional<Wishlist> findByIdAndUser(Long id, User user);
}
