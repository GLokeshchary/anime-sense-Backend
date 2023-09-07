package com.animesense.productservice.repository;

import com.animesense.productservice.models.Order;
import com.animesense.productservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    public List<Order> findByUser(User user);
}
