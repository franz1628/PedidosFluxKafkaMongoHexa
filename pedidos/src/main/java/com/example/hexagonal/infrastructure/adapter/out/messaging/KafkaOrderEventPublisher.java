package com.example.hexagonal.infrastructure.adapter.out.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.hexagonal.application.port.out.OrderEventPublisher;
import com.example.hexagonal.domain.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class KafkaOrderEventPublisher implements OrderEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderEventPublisher.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "pedidos";

    public KafkaOrderEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> publishOrderCreated(Order order) {
        String orderId = order.getId() != null ? order.getId() : "unknown";
        log.info("Publishing order created event to Kafka for order: {}", orderId);
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            return Mono.fromFuture(kafkaTemplate.send(TOPIC, orderId, orderJson).toCompletableFuture())
                    .then();
        } catch (JsonProcessingException e) {
            log.error("Error serializing order to JSON", e);
            return Mono.error(e);
        }
    }
}
