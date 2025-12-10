package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.notification;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Log4jNotificationAdapter implements NotificationPort {
    @Override
    public void notify(String message, String user) {
        log.info("Log4j: NOTIFICACIÓN BANCARIA AL CLIENTE [{}]: {}", user, message);

    }
}
