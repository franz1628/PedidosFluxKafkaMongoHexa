package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Order;
import reactor.core.publisher.Mono;

public interface CreateOrderUseCase {
    Mono<Order> createOrder(Order order);
}
