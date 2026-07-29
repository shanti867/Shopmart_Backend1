package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.Brand;
import com.example.Shopmart_Backend1.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin("*")
public class BrandController {
    @Autowired
    private BrandService service;

    @PostMapping
    public Brand createBrand(@RequestParam("name")String name, @RequestParam("pic")MultipartFile pic, @RequestParam("status")Boolean status)throws Exception{
        return service.save(name, pic, status);
    }
    @GetMapping
    public List<Brand> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Long id, @RequestParam(value = "name", required=false)String name, @RequestParam(value="pic", required = false)MultipartFile pic, @RequestParam(value="status", required = false)Boolean status)throws Exception{
        return service.updateBrand(id, name, pic, status);
    }
}