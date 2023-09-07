package com.animesense.productservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException(String s) {
        super(s);
    }
}
