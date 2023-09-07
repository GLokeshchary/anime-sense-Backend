package com.animesense.productservice.controller;

import com.animesense.productservice.exception.OrderNotFoundException;
import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.Order;
import com.animesense.productservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order,@PathVariable int userId) throws UserNotFoundException {
        return orderService.saveOrder(order,userId);
    }
    @GetMapping("/all/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrdersByUserId(@PathVariable int userId) throws UserNotFoundException {
        return orderService.getAllOrdersByUserId(userId);
    }
    @GetMapping("/allOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/chart")
    @ResponseStatus(HttpStatus.OK)
    public Map<LocalDate,Integer> getDateAndCount(){
        return orderService.getDateAndOrderCount();
    }
    @PostMapping("/status/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateOrderStatusByOrderId(@PathVariable int orderId,@RequestBody String orderStatus) throws OrderNotFoundException {
        return orderService.updateOrderStatusByOrderId(orderId,orderStatus);
    }
}
