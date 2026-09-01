package com.example.Shopmart_Backend1.Repository;

import com.example.Shopmart_Backend1.Entity.Address;
import com.example.Shopmart_Backend1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByUser(User user);
    Optional<Address> findByIdAndUser(Long id, User user);
}
