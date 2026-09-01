package com.example.Shopmart_Backend1.Dto;

import lombok.Data;

@Data
public class CartDTO {
    private Long id;
    private Long ProductId;
    private String name;
    private String brand;
    private String[] pic;
    private String[] color;
    private String[] size;
    private Integer stockQuantity;
    private Integer price;
    private Integer quantity;
    private String selectedColor;
    private String selectedSize;


}
