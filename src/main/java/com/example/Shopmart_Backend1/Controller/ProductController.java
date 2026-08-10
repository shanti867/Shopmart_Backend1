package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Dto.ProductImportRequest;
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
    @PutMapping("/{id}")
    public Product updateproduct(@PathVariable  Long id, @ModelAttribute ProductRequest request)throws Exception{
        return service.update(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable Long id)throws Exception
    {
        service.delete(id);
    }
    @PostMapping("/import")
    public Product importProduct(
            @RequestBody ProductImportRequest request) throws Exception {
        return service.importProduct(request);
    }
}
