package com.animesense.productservice.repository;

import com.animesense.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public boolean existsByProductName(String productName);

}
