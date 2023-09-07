package com.animesense.productservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String productCategory;
    private String animeName;
    private int rating;
    private String gender;
    private List<String> imageUrls=new ArrayList<>();
    private double price;
    private double oldPrice;
    private String stock;
    private int stockQuantity;
    private LocalDateTime createdAt;
    private boolean isNew;
    private String productColor;
    private String productSpecification;
    private LocalDateTime lastUpdated;
}
