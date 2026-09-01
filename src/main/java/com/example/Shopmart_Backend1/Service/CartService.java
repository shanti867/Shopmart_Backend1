package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.CartDTO;
import com.example.Shopmart_Backend1.Entity.Cart;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.CartRepository;
import com.example.Shopmart_Backend1.Repository.ProductRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Map<String, Object> createCart(String loggedInUsername, Cart cart){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }

        if(cart.getProduct() == null || cart.getProduct().getId() == null){
            return Map.of(
                    "status", false,
                    "message", "Product is required"
            );
        }

        Product product = productRepository
                .findById(cart.getProduct().getId())
                .orElse(null);

        if(product == null){
            return Map.of(
                    "status", false,
                    "message", "Product not found"
            );
        }

        Optional<Cart> existingCart = cartRepository.findByUserAndProduct(user, product);
        if(existingCart.isPresent()){
            return Map.of(
                    "status", false,
                    "message", "Product Already Added To Cart"
            );
        }
        cart.setUser(user);
        cart.setProduct(product);
        Cart savedCart = cartRepository.save(cart);

        CartDTO dto = convertToDTO(savedCart);
        return Map.of(
                "status", true,
                "message", "Product Added To Cart",
                "data", dto
        );
    }

    public Map<String,Object> getCart(String loggedInUsername){
        User user = userRepository.findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }
        List<Cart> cartList = cartRepository.findByUser(user);
        List<CartDTO> dtoList = cartList.stream()
                .map(this::convertToDTO)
                .toList();

        return Map.of(
                "status", true,
                "data", dtoList
        );
    }
    public Map<String,Object> deleteCart(String loggedInUsername, Long id){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }

        Cart cart = cartRepository
                .findByIdAndUser(id, user)
                .orElse(null);

        if(cart == null){
            return Map.of(
                    "status", false,
                    "message", "Cart Item not found"
            );
        }
        cartRepository.delete(cart);
        return Map.of(
                "status", true,
                "message", "Cart Item Deleted"
        );

    }
    private CartDTO convertToDTO(Cart cart){
        Product product = cart.getProduct();
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setProductId(product.getId());
        dto.setName(product.getName());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setPrice(product.getFinalPrice());
        dto.setQuantity(cart.getQuantity());
        dto.setSelectedColor(cart.getColor());
        dto.setSelectedSize(cart.getSize());
        return dto;
    }
}
