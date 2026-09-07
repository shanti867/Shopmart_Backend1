package com.example.Shopmart_Backend1.Dto;

import lombok.Data;

@Data
public class WishlistDTO {
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String[] pic;
    private String[] color;
    private String[] size;
    private int stockQuantity;
    private int price;

}
