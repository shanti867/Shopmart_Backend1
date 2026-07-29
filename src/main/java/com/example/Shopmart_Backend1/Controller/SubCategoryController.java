package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.SubCategory;
import com.example.Shopmart_Backend1.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
@CrossOrigin("*")
public class SubCategoryController {

    @Autowired
    private SubCategoryService service;

    @PostMapping
    public SubCategory createCategory(@RequestParam("name")String name, @RequestParam("pic") MultipartFile pic, @RequestParam("status") Boolean status)throws Exception{
        return service.save(name, pic, status);
    }
    @GetMapping
    public List<SubCategory> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        service.delete(id);
    }
    @PutMapping("/{id}")
    public SubCategory updateCategory(@PathVariable Long id, @RequestParam (value = "name", required=false)String name, @RequestParam(value = "pic", required = false)MultipartFile pic, @RequestParam(value = "status", required=false)Boolean status)throws Exception{
        return service.update(id, name, pic, status);
    }
}