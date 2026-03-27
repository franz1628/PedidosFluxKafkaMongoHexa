package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface GetOrderUseCase {
    Optional<Order> getOrderById(String id);
    List<Order> getAllOrders();
}
