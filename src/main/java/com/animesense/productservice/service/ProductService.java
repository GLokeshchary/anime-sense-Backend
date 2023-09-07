package com.animesense.productservice.service;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.payload.ProductRequest;
import com.animesense.productservice.payload.ProductResponse;
import com.animesense.productservice.payload.ProductResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public ProductResponseMessage saveProduct(ProductRequest productRequest, int merchantId) throws UserNotFoundException, ProductNotFoundException;

    public List<ProductResponse> getProductsByCategory(String categoryName);

    public List<ProductResponse> getProductsByAnimeName(String animeName);

    public ProductResponseMessage updateProductById(ProductRequest productRequest,int productId) throws ProductNotFoundException, UserNotFoundException;

    public String deleteProductById(int productId) throws ProductNotFoundException;

    public List<ProductResponse> getAllProducts() throws ProductNotFoundException;

    public ProductResponse getProductById(int id) throws ProductNotFoundException;

   // public ProductResponse getProductsByAnimeName(String animeName);

}
