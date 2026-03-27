package com.example.inventarios.infrastructure.adapter.in.kafka;

import com.example.inventarios.application.port.in.ProcessOrderUseCase;
import com.example.inventarios.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderConsumer.class);

    private final ProcessOrderUseCase processOrderUseCase;

    public KafkaOrderConsumer(ProcessOrderUseCase processOrderUseCase) {
        this.processOrderUseCase = processOrderUseCase;
    }

    @KafkaListener(topics = "pedidos", groupId = "inventarios-group")
    public void consume(Order order) {
        log.info("Consumed Order from Kafka: {}", order);
        processOrderUseCase.processOrder(order);
    }
}
