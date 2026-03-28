package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepositoryPort {
    Mono<Order> save(Order order);
    Mono<Order> findById(String id);
    Flux<Order> findAll();
    Mono<Void> deleteById(String id);
}
