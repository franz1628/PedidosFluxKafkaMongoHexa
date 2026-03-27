package com.example.notificaciones.infrastructure.adapter.in.rabbitmq;

import com.example.notificaciones.application.port.in.SendNotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final SendNotificationUseCase sendNotificationUseCase;

    public RabbitMQConsumer(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @RabbitListener(queues = "notifications")
    public void consume(String message) {
        log.info("Received from RabbitMQ: {}", message);
        sendNotificationUseCase.sendNotification(message, "customer@example.com");
    }
}
