package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Dto.ProductRequest;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping
    public Product createProduct(@ModelAttribute ProductRequest request)throws Exception{
        return service.save(request);
    }
    @GetMapping
    public List<Product> getProducts(){
        return service.getAllProducts();
    }
}
