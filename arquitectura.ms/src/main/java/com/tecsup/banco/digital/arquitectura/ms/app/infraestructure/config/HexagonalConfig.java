package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.config;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.*;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.AccountRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.ClientRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.NotificationPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.TransactionRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.usecase.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryPort clientRepository) {
        return new CreateClientUseCaseImpl(clientRepository);
    }

    @Bean
    public FindClientUseCase findClientUseCase(ClientRepositoryPort clientRepository) {
        return new FindClientUseCaseImpl(clientRepository);
    }

    @Bean
    public FindAccountUseCase findAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        return new FindAccountUseCaseImpl(accountRepositoryPort);
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase(
            AccountRepositoryPort accountRepositoryPort,
            ClientRepositoryPort clientRepositoryPort) {

        return new CreateAccountUseCaseImpl(accountRepositoryPort, clientRepositoryPort);
    }

    @Bean
    public FindTransactionUseCase findTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new FindTransactionUseCaseImpl(transactionRepositoryPort);
    }

    /*
    * ACA DEFINO que notificationPort va implementar log4jNotificationAdapter para notificar mediante log4j usando
    * @Qualifier("log4jNotificationAdapter")
    * TAMBIEN PUEDO DEIFNIR que notificationPort va implementar consoleNotificationAdapter para notificar mediante consola usando
    * @Qualifier("consoleNotificationAdapter")
    * ESTO RESPONDE AL patron adapter
    * */
    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            TransactionRepositoryPort transactionRepositoryPort,
            AccountRepositoryPort accountRepositoryPort,
            @Qualifier("log4jNotificationAdapter") NotificationPort notificationPort
            //@Qualifier("consoleNotificationAdapter") NotificationPort notificationPort
    ) {

        return new CreateTransactionUseCaseImpl(transactionRepositoryPort, accountRepositoryPort, notificationPort);
    }

}
