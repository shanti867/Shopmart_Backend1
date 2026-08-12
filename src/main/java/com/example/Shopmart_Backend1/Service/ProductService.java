package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.ProductImportRequest;
import com.example.Shopmart_Backend1.Dto.ProductRequest;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.SubCategory;

import java.util.List;

public interface ProductService {
    public Product save(ProductRequest request)throws Exception;
    public List<Product> getAllProducts();
    public Product update(Long id, ProductRequest request)throws Exception;
    public void delete (Long id)throws Exception;
    public Product importProduct(ProductImportRequest request) throws Exception;
    public List<Product> getActive();
}
