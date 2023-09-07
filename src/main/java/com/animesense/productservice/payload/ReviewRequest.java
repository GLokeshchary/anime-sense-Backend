package com.animesense.productservice.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String reviewerName;
    private String email;
    private int rating;
    private String reviewTitle;
    private String reviewComments;
}
