package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.model.Order;
import reactor.core.publisher.Mono;

public interface OrderEventPublisher {
    Mono<Void> publishOrderCreated(Order order);
}
