package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.WishlistDTO;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Entity.Wishlist;
import com.example.Shopmart_Backend1.Repository.ProductRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import com.example.Shopmart_Backend1.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Map<String,Object> createWishlist(String loggedInUsername, WishlistDTO wishlistDTO){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status",false,
                    "message","User not found"
            );
        }
        if(wishlistDTO.getProductId() == null){
            return Map.of(
                    "status",false,
                    "message","Product is required"
            );
        }

        Product product = productRepository
                .findById(wishlistDTO.getProductId())
                .orElse(null);

        if(product == null){
            return Map.of(
                    "status",false,
                    "message","Product not found"
            );
        }
        Optional<Wishlist> existingWishlist = wishlistRepository.findByUserAndProduct(user,product);
        if(existingWishlist.isPresent()){
            return Map.of(
                    "status",false,
                    "message","Product Already Added To Wishlist"
            );
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        WishlistDTO dto = convertToDTO(savedWishlist);

        return Map.of(
                "status", true,
                "message", "Product Added To Wishlist",
                "data", dto
        );
    }

    public Map<String,Object> getWishlist(String loggedInUsername){
        User user  = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status",false,
                    "message", "User not found"
            );
        }
        List<Wishlist> wishlistList = wishlistRepository.findByUser(user);
        List<WishlistDTO> dtoList = wishlistList
                .stream()
                .map(this::convertToDTO)
                .toList();

        return Map.of(
                "status",true,
                "data",dtoList
        );
    }
    public Map<String,Object> deleteWishlist(String loggedInUsername, Long id){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status",false,
                    "message","User not found"
            );
        }
        Wishlist wishlist = wishlistRepository
                .findByIdAndUser(id, user)
                .orElse(null);

        if(wishlist == null){
            return Map.of(
                 "status",false,
                 "message","Wishlist Item not found"
            );
        }
        wishlistRepository.delete(wishlist);
        return Map.of(
                "status",true,
                "message","Wishlist Item Deleted"
        );
    }

    private WishlistDTO convertToDTO(Wishlist wishlist){
        Product product  = wishlist.getProduct();
        WishlistDTO dto = new WishlistDTO();

        dto.setId(wishlist.getId());
        dto.setProductId(product.getId());
        dto.setName(product.getName());
        if(product.getBrand() != null){
            dto.setBrand(product.getBrand().getName());
        }
        dto.setPic(product.getPic().toArray(new String[0]));
        dto.setColor(product.getColor().toArray(new String[0]));
        dto.setSize(product.getSize().toArray(new String[0]));

        dto.setStockQuantity(product.getStockQuantity());
        dto.setPrice(product.getFinalPrice());

        return dto;

    }

}
