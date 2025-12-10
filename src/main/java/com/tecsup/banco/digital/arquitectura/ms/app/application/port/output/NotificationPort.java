package com.tecsup.banco.digital.arquitectura.ms.app.application.port.output;

public interface NotificationPort {

    void notify(String message, String user);
}
