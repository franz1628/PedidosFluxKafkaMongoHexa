package com.example.notificaciones.application.port.in;

public interface SendNotificationUseCase {
    void sendNotification(String message, String recipient);
}
