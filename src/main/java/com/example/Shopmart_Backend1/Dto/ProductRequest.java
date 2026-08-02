package com.example.Shopmart_Backend1.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private Long mainCategoryId;
    private Long subCategoryId;
    private Long brandId;
    private List<String> color;
    private List<String> size;
    private int basePrice;
    private int discount;
    private int finalPrice;
    private Boolean stock;
    private int stockQuantity;
    private String description;
    private MultipartFile[] pic;
    private Boolean status;


}
