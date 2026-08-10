package com.example.Shopmart_Backend1.Controller;

import com.example.Shopmart_Backend1.Entity.MainCategory;
import com.example.Shopmart_Backend1.Service.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/maincategory")
@CrossOrigin("*")
public class MainCategoryController {

    @Autowired
    private MainCategoryService service;

    @PostMapping
    public MainCategory  createCategory(
            @RequestParam("name") String name,
            @RequestParam("pic") MultipartFile pic,
            @RequestParam("status") Boolean status
    ) throws Exception {

        return service.save(name, pic, status);
    }
    @GetMapping
    public List<MainCategory> getAll() {

        return service.getAll();
    }
    @GetMapping("/active")
    public List<MainCategory> getActiveMainCategory(){
        return service.getActive();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)throws Exception{
        service.delete(id);
    }
    @PutMapping("/{id}")
    public MainCategory update(
            @PathVariable Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pic", required = false) MultipartFile pic,
            @RequestParam(value = "status", required = false) Boolean status
    ) throws Exception {

        return service.update(id, name, pic, status);
    }
}