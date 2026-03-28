package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetOrderUseCase {
    Mono<Order> getOrderById(String id);
    Flux<Order> getAllOrders();
}
