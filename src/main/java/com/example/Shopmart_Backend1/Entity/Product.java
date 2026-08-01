package com.example.Shopmart_Backend1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ProductId;

    private String name;

    @ManyToOne
    @JoinColumn(name="maincategory_id")
    private MainCategory maincategory;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subcategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ElementCollection
    private List<String> color;

    @ElementCollection
    private List<String> size;

    private int basePrice;
    private int discount;
    private int finalPrice;
    private Boolean stock;
    private int stockQuantity;

    @Lob
    private  String Description;

    @ElementCollection
    private List<String> pic;

    private Boolean status;
}
