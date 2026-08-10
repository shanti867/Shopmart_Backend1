package com.example.Shopmart_Backend1.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductImportRequest {

    private String name;

    private String maincategory;
    private String subcategory;
    private String brand;

    private List<String> color;
    private List<String> size;

    private int basePrice;
    private int discount;
    private int finalPrice;

    private Boolean stock;
    private int stockQuantity;

    private List<String> pic;

    private Boolean status;
    private String description;
}