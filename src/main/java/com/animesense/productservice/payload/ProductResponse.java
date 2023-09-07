package com.animesense.productservice.payload;

import com.animesense.productservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private String productCategory;
    private String animeName;
    private int rating;
    private String gender;
    private double price;
    private double oldPrice;
    private String stock;
    private List<String> imageUrls=new ArrayList<>();
    private int stockQuantity;
    private LocalDateTime createdAt;
    private boolean isNew;
    private String productColor;
    private String productSpecification;
    private LocalDateTime lastUpdated;
    private User user ;
    private String message;
}
