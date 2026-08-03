package com.example.Shopmart_Backend1.Service;


import com.example.Shopmart_Backend1.Dto.ProductRequest;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Repository.BrandRepository;
import com.example.Shopmart_Backend1.Repository.MainCategoryRepository;
import com.example.Shopmart_Backend1.Repository.ProductRepository;
import com.example.Shopmart_Backend1.Repository.SubCategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private MainCategoryRepository maincategoryRepo;
    @Autowired
    private SubCategoryRepository subcategoryRepo;
    @Autowired
    private BrandRepository brandRepo;

    public Product save(ProductRequest request)throws Exception{
        Product product = new Product();
        product.setName(request.getName());
        product.setMaincategory(
                maincategoryRepo.findById(request.getMainCategoryId()).orElseThrow()
        );
        product.setSubcategory(
                subcategoryRepo.findById(request.getSubCategoryId()).orElseThrow()
        );
        product.setBrand(
                brandRepo.findById(request.getBrandId()).orElseThrow()
        );
        product.setColor(request.getColor());
        product.setSize(request.getSize());
        product.setBasePrice(request.getBasePrice());
        product.setDiscount(request.getDiscount());
        product.setFinalPrice(request.getFinalPrice());
        product.setStock(request.getStock());
        product.setStockQuantity(request.getStockQuantity());
        product.setDescription(request.getDescription());

        List<String> images = new ArrayList<>();
        String uploadDir = System.getProperty("user.dir")
                + File.separator
                + "uploads"
                + File.separator
                + "product";
        File directory = new File(uploadDir);
        if(!directory.exists()){
            directory.mkdirs();
        }
        for(MultipartFile file: request.getPic()){
            String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            File destination = new File(directory, fileName);
            file.transferTo(destination);
            images.add(fileName);
        }
        product.setPic(images);
        product.setStatus(request.getStatus());

        Product savedProduct = productRepo.save(product);
        savedProduct.setProductId(
                "PRD" + String.format("%03d", savedProduct.getId())
        );
        return productRepo.save(savedProduct);
    }
    public List<Product> getAllProducts(){

        return productRepo.findAll();
    }
    public Product update(Long id, ProductRequest request)throws Exception {
        Product product = productRepo.findById(id).orElseThrow();

        product.setName(request.getName());
        product.setMaincategory(maincategoryRepo.findById(request.getMainCategoryId()).orElseThrow());
        product.setSubcategory(subcategoryRepo.findById(request.getSubCategoryId()).orElseThrow());
        product.setBrand(brandRepo.findById(request.getBrandId()).orElseThrow());
        product.setColor(request.getColor());
        product.setSize(request.getSize());
        product.setBasePrice(request.getBasePrice());
        product.setDiscount(request.getDiscount());
        product.setFinalPrice(request.getFinalPrice());
        product.setStock(request.getStock());
        product.setStockQuantity(request.getStockQuantity());
        product.setDescription(request.getDescription());

        String uploadDir = System.getProperty("user.dir")
                + File.separator
                + "uploads"
                + File.separator
                + "product";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        ObjectMapper mapper = new ObjectMapper();
        List<String> images = mapper.readValue(request.getOldPic(), new TypeReference<List<String>>() {
        });
        List<String> oldDatabaseImages = product.getPic();

        for (String image : oldDatabaseImages) {
            if (!images.contains(image)) {
                Path path = Paths.get(uploadDir + image);
                Files.deleteIfExists(path);
            }
        }
            if (request.getPic() != null) {
                for (MultipartFile file : request.getPic()) {
                    if (!file.isEmpty()) {
                        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                        File destination = new File(directory , fileName);
                        file.transferTo(destination);
                        images.add(fileName);
                    }
                }
            }
            product.setPic(images);
            product.setStatus(request.getStatus());
            return productRepo.save(product);
        }
    public void delete(Long id){

        productRepo.deleteById(id);
    }

}
