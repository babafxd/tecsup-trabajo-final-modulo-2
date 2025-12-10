package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.notification;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.NotificationPort;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNotificationAdapter implements NotificationPort {
    @Override
    public void notify(String message, String user) {
        System.out.println("Consola: NOTIFICACIÓN BANCARIA AL CLIENTE [" + user + "]: " + message);
    }
}
