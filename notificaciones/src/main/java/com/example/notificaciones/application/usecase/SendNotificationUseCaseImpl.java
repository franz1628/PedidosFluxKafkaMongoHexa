package com.example.notificaciones.application.usecase;

import com.example.notificaciones.application.port.in.SendNotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {
    private static final Logger log = LoggerFactory.getLogger(SendNotificationUseCaseImpl.class);
    @Override
    public void sendNotification(String message, String recipient) {
        log.info("Sending Notification: To={}, Message={}", recipient, message);
    }
}
