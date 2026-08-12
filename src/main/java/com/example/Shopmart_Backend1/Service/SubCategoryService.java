package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.MainCategory;
import com.example.Shopmart_Backend1.Entity.SubCategory;
import com.example.Shopmart_Backend1.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepository repository;

    public SubCategory save(String name, MultipartFile pic, Boolean status)throws Exception{
        File uploadFolder = new File(System.getProperty("user.dir"),"uploads/subcategory");

        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        String imageName = pic.getOriginalFilename();
        File destination = new File(uploadFolder,imageName);
        pic.transferTo(destination);

        SubCategory category = new SubCategory();
        category.setName(name);
        category.setPic(imageName);
        category.setStatus(status);

        SubCategory savedCategory = repository.save(category);
        savedCategory.setSubCategoryId("SBC" + String.format("%03d",savedCategory.getId()));
        return repository.save(savedCategory);
    }
    public List<SubCategory> getAll(){
        return repository.findAll();
    }
    public List<SubCategory> getActive(){

        return repository.findByStatusTrue();
    }
    public void delete(Long id)throws Exception{
        SubCategory subcategory = repository.findById(id).orElseThrow();
        String imageName = subcategory.getPic();
        if(imageName != null && !imageName.isEmpty()){
            File uploadFolder = new File(System.getProperty(("user.dir"),"uploads/subcategory"));

            File imageFile = new File(uploadFolder,imageName);
            if(uploadFolder.exists()){
                imageFile.delete();
            }
        }
        repository.deleteById(id);
    }
    public SubCategory update(Long id, String name, MultipartFile pic, Boolean status)throws Exception{
        SubCategory category = repository.findById(id).orElseThrow();

        if(name != null && !name.trim().isEmpty()){
            category.setName(name);
        }
        File uploadFolder = new File(System.getProperty("user.dir"),"uploads/subcategory");
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        if(pic !=null && !pic.isEmpty()){
            String imageName = pic.getOriginalFilename();
            File destination = new File(uploadFolder, imageName);
            pic.transferTo(destination);
            category.setPic(imageName);
        }
        if(status!=null){
            category.setStatus(status);
        }
        return repository.save(category);
    }
}