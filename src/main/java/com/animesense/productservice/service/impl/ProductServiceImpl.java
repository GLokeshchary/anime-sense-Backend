package com.animesense.productservice.service.impl;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.Product;
import com.animesense.productservice.models.User;
import com.animesense.productservice.payload.ProductRequest;
import com.animesense.productservice.payload.ProductResponse;
import com.animesense.productservice.payload.ProductResponseMessage;
import com.animesense.productservice.repository.ProductRepository;
import com.animesense.productservice.repository.UserRepository;
import com.animesense.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ProductResponseMessage saveProduct(ProductRequest productRequest, int merchantId) throws UserNotFoundException, ProductNotFoundException {
        log.info("Starting the SaveProduct Method");
        boolean exists=productRepository.existsByProductName(productRequest.getProductName());
        if(exists){
            return new ProductResponseMessage("Product with "+productRequest.getProductName()+" already exists in DB");
        }
        User user=userRepository.findById(merchantId).orElseThrow(()->new UserNotFoundException("User with "+merchantId+" not Found"));
        if(user != null){
            log.info("User is not null");
            Product product=modelMapper.map(productRequest, Product.class);
            product.setCreatedAt(LocalDateTime.now());
            product.setUser(user);
            productRepository.save(product);
            log.info("Product Has Been Saved in DB");
            return new ProductResponseMessage("Product Has Been Saved in DB") ;
        }
        else {
            throw new UserNotFoundException("User is empty ");
        }
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName) {
        List<Product> products=productRepository.findAll();
        if(!products.isEmpty()){
            List<Product> productsByCategory=products.stream().filter(product -> product.getProductCategory().equals(categoryName)).toList();
            return productsByCategory.stream().map(product -> modelMapper.map(product,ProductResponse.class)).toList();
        }
        return null;
    }

    @Override
    public List<ProductResponse> getProductsByAnimeName(String animeName) {
        List<Product> products=productRepository.findAll();
        if(!products.isEmpty()){
            List<Product> productsByAnimeName=products.stream().filter(product -> product.getAnimeName().equals(animeName)).toList();
            return productsByAnimeName.stream().map(product -> modelMapper.map(product,ProductResponse.class)).toList();
        }
        return null;
    }

    @Override
    public ProductResponseMessage updateProductById(ProductRequest productRequest, int productId) throws ProductNotFoundException, UserNotFoundException {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product with "+productId+"not Found"));
        if(product!=null){
            product.setProductName(productRequest.getProductName());
            product.setProductCategory(productRequest.getProductCategory());
            product.setAnimeName(productRequest.getAnimeName());
            product.setGender(productRequest.getGender());
            product.setPrice(productRequest.getPrice());
            product.setOldPrice(productRequest.getOldPrice());
            product.setStock(productRequest.getStock());
            product.setStockQuantity(productRequest.getStockQuantity());
            product.setProductColor(productRequest.getProductColor());
            product.setProductSpecification(productRequest.getProductSpecification());
            product.setLastUpdated(LocalDateTime.now());

            productRepository.save(product);
            return new ProductResponseMessage("Updated the Product with "+productId);
        }
        return new ProductResponseMessage("Update Failed For Product "+productId);
    }

    @Override
    public String deleteProductById(int productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProducts() throws ProductNotFoundException {
        List<Product> products=productRepository.findAll();
        if(!products.isEmpty()){
            return products.stream().map(product -> modelMapper.map(product,ProductResponse.class)).toList();
        }
        else {
            throw new ProductNotFoundException("Product List is Empty");
        }
    }

    @Override
    public ProductResponse getProductById(int productId) throws ProductNotFoundException {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product with "+productId+" NOt Found"));
        if(product!=null) {
            return modelMapper.map(product, ProductResponse.class);
        }
        return null;
    }
}
