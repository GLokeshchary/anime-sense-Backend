package com.animesense.productservice.repository;

import com.animesense.productservice.models.Product;
import com.animesense.productservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByProduct(Product product);

}
