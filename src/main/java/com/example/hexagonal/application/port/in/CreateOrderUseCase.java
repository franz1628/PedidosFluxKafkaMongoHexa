package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(Order order);
}
