package com.animesense.productservice.service;

import com.animesense.productservice.exception.OrderNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    public Order saveOrder(Order order,int userId) throws UserNotFoundException;
    public List<Order> getAllOrdersByUserId(int userId) throws UserNotFoundException;
    public List<Order> getAllOrders ();
    public String updateOrderStatusByOrderId(int orderId,String orderStatus) throws OrderNotFoundException;
    public Map<LocalDate,Integer> getDateAndOrderCount();
}
