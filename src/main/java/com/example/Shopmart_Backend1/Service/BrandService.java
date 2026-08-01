package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Brand;

import com.example.Shopmart_Backend1.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository repository;
    public Brand save(String name, MultipartFile pic, Boolean status)throws Exception{
        File uploadFolder = new File(System.getProperty("user.dir"), "uploads/brand");
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }

        String imageName = pic.getOriginalFilename();
        File destination = new File(uploadFolder, imageName);
        pic.transferTo(destination);
        // Save data into database
        Brand brand = new Brand();
        brand.setName(name);
        brand.setPic(imageName);
        brand.setStatus(status);

        Brand savedBrand = repository.save(brand);
        savedBrand.setBrandId("BR"+String.format("%03d", savedBrand.getId()));
        return repository.save(savedBrand);
    }
    public List<Brand> getAll(){

        return repository.findAll();
    }
    public List<Brand> getActive(){
        return repository.findByStatusTrue();
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public Brand updateBrand(Long id, String name, MultipartFile pic, Boolean status)throws Exception{
        Brand brand = repository.findById(id).orElseThrow();

        if(name != null && !name.trim().isEmpty()){
            brand.setName(name);
        }
        File uploadFolder = new File(System.getProperty("user.dir"), "uploads/brand");
        if(!uploadFolder.exists()){
            uploadFolder.mkdir();
        }
        if(pic != null && !pic.isEmpty()){
            String imageName = pic.getOriginalFilename();
            File destination = new File(uploadFolder, imageName);
            pic.transferTo(destination);
            brand.setPic(imageName);
        }
        if(status != null){
            brand.setStatus(status);
        }
        return repository.save(brand);
    }
}