package com.animesense.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String productCategory;
    private String animeName;
    private int rating;
    private String gender;
    private double price;
    private double oldPrice;
    private String stock;
    private int stockQuantity;
    private LocalDateTime createdAt;
    private boolean isNew;
    private String productColor;
    private String productSpecification;
    private LocalDateTime lastUpdated;

    @ElementCollection (targetClass = String.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "imageUrls", joinColumns = @JoinColumn(name = "imageId"))
    @Column(name = "imageUrl",nullable = false)
    private List<String> imageUrls=new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user ;


    @OneToMany(mappedBy = "product",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Review> reviews=new ArrayList<>();
}
