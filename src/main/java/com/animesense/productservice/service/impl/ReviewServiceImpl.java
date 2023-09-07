package com.animesense.productservice.service.impl;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.ReviewNotFoundException;
import com.animesense.productservice.models.Product;
import com.animesense.productservice.models.Review;
import com.animesense.productservice.payload.ReviewRequest;
import com.animesense.productservice.repository.ProductRepository;
import com.animesense.productservice.repository.ReviewRepository;
import com.animesense.productservice.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Review> getAllReviews() throws ReviewNotFoundException {
        List<Review> reviews=reviewRepository.findAll();
        if(!reviews.isEmpty()){
            return reviews;
        }
        throw new ReviewNotFoundException("Reviews Not Found");
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) throws ProductNotFoundException, ReviewNotFoundException {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product with "+productId+" Not Found"));
        List<Review> reviews=reviewRepository.findByProduct(product);
        if(!reviews.isEmpty()){
            return reviews;
        }
        else{
            return null;
        }
    }

    @Override
    public String saveReviewByProductId(ReviewRequest reviewRequest,int productId) throws ProductNotFoundException {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product with "+productId+" doesn't exist"));
        if(product!=null){
            Review review=modelMapper.map(reviewRequest,Review.class);
            review.setReviewProductImage(product.getImageUrls().get(0));
            review.setProduct(product);
            review.setReviewCreatedAt(LocalDateTime.now());
            reviewRepository.save(review);
            return "Review has Been Successfully Saved for "+productId+" in DB";
        }
        return "Review Has not been Saved for "+productId+" InDb";
    }

    @Override
    public String updateReviewByReviewId(ReviewRequest reviewRequest, int reviewId) {
        return null;
    }
}
