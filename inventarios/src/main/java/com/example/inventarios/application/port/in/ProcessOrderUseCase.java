package com.example.inventarios.application.port.in;

import com.example.inventarios.domain.model.Order;
import reactor.core.publisher.Mono;

public interface ProcessOrderUseCase {
    Mono<Void> processOrder(Order order);
}
