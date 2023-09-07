package com.animesense.productservice.service;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.ReviewNotFoundException;
import com.animesense.productservice.models.Review;
import com.animesense.productservice.payload.ReviewRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviews() throws ReviewNotFoundException;
    public List<Review> getReviewsByProductId(int productId) throws ProductNotFoundException, ReviewNotFoundException;
    public String saveReviewByProductId(@RequestBody ReviewRequest reviewRequest,int productId) throws ProductNotFoundException;
    public String updateReviewByReviewId(@RequestBody ReviewRequest reviewRequest,int reviewId);
}
