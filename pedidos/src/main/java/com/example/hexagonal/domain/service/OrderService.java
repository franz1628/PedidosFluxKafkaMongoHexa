package com.example.hexagonal.domain.service;

import com.example.hexagonal.application.port.in.CreateOrderUseCase;
import com.example.hexagonal.application.port.in.GetOrderUseCase;
import com.example.hexagonal.application.port.out.OrderEventPublisher;
import com.example.hexagonal.application.port.out.OrderRepositoryPort;
import com.example.hexagonal.domain.model.Order;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepositoryPort orderRepositoryPort, OrderEventPublisher orderEventPublisher) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    public Mono<Order> createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }
        return orderRepositoryPort.save(order)
                .flatMap(savedOrder -> orderEventPublisher.publishOrderCreated(savedOrder)
                        .thenReturn(savedOrder));
    }

    @Override
    public Mono<Order> getOrderById(String id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }

    public Mono<Void> deleteOrder(String id) {
        return orderRepositoryPort.deleteById(id);
    }
}
