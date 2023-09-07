package com.animesense.productservice.controller;

import com.animesense.productservice.exception.ProductNotFoundException;
import com.animesense.productservice.exception.ReviewNotFoundException;
import com.animesense.productservice.models.Review;
import com.animesense.productservice.payload.ReviewRequest;
import com.animesense.productservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/save/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveReviewByProductId(@RequestBody ReviewRequest reviewRequest,@PathVariable int productId) throws ProductNotFoundException {
        return reviewService.saveReviewByProductId(reviewRequest,productId);
    }
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByProductId(@PathVariable int productId) throws ReviewNotFoundException, ProductNotFoundException {
        return reviewService.getReviewsByProductId(productId);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getAllReviews() throws ReviewNotFoundException {
        return  reviewService.getAllReviews();
    }
}
