package com.animesense.productservice.service.impl;

import com.animesense.productservice.exception.OrderNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.CartItem;
import com.animesense.productservice.models.Order;
import com.animesense.productservice.models.User;
import com.animesense.productservice.repository.OrderRepository;
import com.animesense.productservice.repository.UserRepository;
import com.animesense.productservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public Order saveOrder(Order order,int userId) throws UserNotFoundException {
        User user= userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        order.setCreatedAt(LocalDateTime.now());
        order.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(6));
        order.setOrderStatus("Shipping");
        order.setUser(user);
        if(order.getRazorPayTransactionId()!=null){
            order.setAmountPaid(true);
        }
        for(CartItem cartItem:order.getCartItems()){

            cartItem.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
        List<Order> orders=orderRepository.findByUser(user);
        if(!orders.isEmpty()){
            return orders;
        }
        return null;
    }
    @Override
    public List<Order> getAllOrders(){
        List<Order> orders=orderRepository.findAll();
        return orders;
    }

    @Override
    public String updateOrderStatusByOrderId(int orderId,String orderStatus) throws OrderNotFoundException {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order Not Found"));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return "Status Updated";
    }

    @Override
    public Map<LocalDate, Integer> getDateAndOrderCount() {
        List<Order> orders=orderRepository.findAll();
        Map<LocalDate, Integer> dateAndOrderCountMap = new HashMap<>();

        for (Order order : orders) {
            LocalDate orderDate = order.getCreatedAt().toLocalDate();
            dateAndOrderCountMap.put(orderDate, dateAndOrderCountMap.getOrDefault(orderDate, 0) + 1);
        }
        return dateAndOrderCountMap;
    }
}
