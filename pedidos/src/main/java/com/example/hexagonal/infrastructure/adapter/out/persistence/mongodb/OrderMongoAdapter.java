package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import com.example.hexagonal.application.port.out.OrderRepositoryPort;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMongoAdapter implements OrderRepositoryPort {

    private final MongoOrderRepository mongoOrderRepository;

    public OrderMongoAdapter(MongoOrderRepository mongoOrderRepository) {
        this.mongoOrderRepository = mongoOrderRepository;
    }

    @Override
    public Mono<Order> save(Order order) {
        return Mono.just(mapToEntity(order))
                .flatMap(mongoOrderRepository::save)
                .map(this::mapToDomain);
    }

    @Override
    public Mono<Order> findById(String id) {
        return mongoOrderRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Flux<Order> findAll() {
        return mongoOrderRepository.findAll().map(this::mapToDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoOrderRepository.deleteById(id);
    }

    private OrderEntity mapToEntity(Order order) {
        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(item -> OrderItemEntity.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderEntity.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .items(itemEntities)
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    private Order mapToDomain(OrderEntity entity) {
        List<OrderItem> items = entity.getItems().stream()
                .map(itemEntity -> new OrderItem(
                        itemEntity.getProductId(),
                        itemEntity.getQuantity(),
                        itemEntity.getPrice()))
                .collect(Collectors.toList());

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                items,
                entity.getTotalAmount(),
                entity.getStatus(),
                entity.getCreatedAt());
    }
}
