package com.animesense.productservice.service.impl;

import com.animesense.productservice.models.CartItem;
import com.animesense.productservice.repository.CartItemRepository;
import com.animesense.productservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        cartItem.setCartItemPrice(cartItem.getProduct().getPrice());
        cartItem.setEstimatedTime(LocalDateTime.now().plusDays(6));
            return cartItemRepository.save(cartItem);
    }
}
