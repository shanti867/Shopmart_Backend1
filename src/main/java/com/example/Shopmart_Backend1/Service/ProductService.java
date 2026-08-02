package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.ProductRequest;
import com.example.Shopmart_Backend1.Entity.Product;

import java.util.List;

public interface ProductService {
    public Product save(ProductRequest request)throws Exception;
    public List<Product> getAllProducts();
}
