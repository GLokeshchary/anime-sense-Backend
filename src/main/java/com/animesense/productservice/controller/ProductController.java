package com.animesense.productservice.controller;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.payload.ProductRequest;
import com.animesense.productservice.payload.ProductResponse;
import com.animesense.productservice.payload.ProductResponseMessage;
import com.animesense.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @PostMapping("/save/{merchantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseMessage saveProductByMerchantId(@RequestBody ProductRequest productRequest, @PathVariable int merchantId) throws UserNotFoundException, ProductNotFoundException {
       return  productService.saveProduct(productRequest,merchantId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() throws ProductNotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable int productId) throws ProductNotFoundException {
        return productService.getProductById(productId);
    }

    @GetMapping("/category/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductByCategory(@PathVariable String name){
        return productService.getProductsByCategory(name);
    }
    @GetMapping("/anime/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductByAnimeName(@PathVariable String name){
        return productService.getProductsByAnimeName(name);

    }
    @PostMapping("/update/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseMessage updateProductByProductId(@PathVariable int productId,@RequestBody ProductRequest productRequest) throws UserNotFoundException, ProductNotFoundException {
        return productService.updateProductById(productRequest,productId);
    }
}
