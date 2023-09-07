package com.animesense.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String razorPayTransactionId;
    private String paymentMethod;
    private String shippingAddress;
    private String orderStatus;
    private double totalOrderPrice;
    private boolean amountPaid;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryDate;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems=new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
