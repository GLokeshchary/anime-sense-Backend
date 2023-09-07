package com.animesense.productservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String s){
        super(s);
    }
}
