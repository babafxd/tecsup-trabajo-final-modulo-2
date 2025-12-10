# 🏦 SISTEMA DE GESTIÓN BANCARIA
# INSTRUCCIONES BÁSICAS DE EJECUCIÓN 
Proyecto para realizar **TRANSFERENCIAS**, **DEPÓSITOS** y **RETIROS**, con validaciones y notificaciones por consola.

---
## Requisitos

- Java **21**
- Maven **3.8+**
- Spring Boot **3.x**
- Base de datos **H2** (en memoria)

---

## ▶️ Ejecutar el proyecto en:

```
com.tecsup.banco.digital.arquitectura.ms.app.BancoDigitalApplication;
```

Servicio por defecto:\
`http://localhost:8080`

------------------------------------------------------------------------
## También se adjunto archivo Postman de las peticiones: TECSUP - MODULO 2.postman_collection.json

## Endpoint consulta datos

## Listar clientes

**GET** `http://localhost:8080/api/client`

------------------------------------------------------------------------

### Respuesta Exitosa

``` json
[
    {
        "id": "CLI001",
        "nombre": "Juan Pérez",
        "email": "juan.perez@email.com",
        "documento": "12345678"
    },
    {
        "id": "CLI002",
        "nombre": "María García",
        "email": "maria.garcia@email.com",
        "documento": "87654321"
    },
    {
        "id": "CLI003",
        "nombre": "Carlos López",
        "email": "carlos.lopez@email.com",
        "documento": "11223344"
    }
]
```

## Listar cuentas bancarias

**GET** `http://localhost:8080/api/account`

------------------------------------------------------------------------

### Respuesta Exitosa

``` json
[
    {
        "cuentaId": "CTA001",
        "clienteId": "CLI001",
        "numeroCuenta": "0001-234567",
        "saldo": 1000.00,
        "estado": "ACTIVO"
    },
    {
        "cuentaId": "CTA002",
        "clienteId": "CLI002",
        "numeroCuenta": "0001-234568",
        "saldo": 2500.00,
        "estado": "ACTIVO"
    },
    {
        "cuentaId": "CTA003",
        "clienteId": "CLI003",
        "numeroCuenta": "0001-234569",
        "saldo": 500.00,
        "estado": "ACTIVO"
    },
    {
        "cuentaId": "CTA004",
        "clienteId": "CLI004",
        "numeroCuenta": "0001-234573",
        "saldo": 15000.88,
        "estado": "ACTIVO"
    }
]
```
## Listar transacciones bancarias

**GET** `http://localhost:8080/api/transaction`

------------------------------------------------------------------------

### Respuesta Exitosa

``` json
[
    {
        "transaccionId": "TXN001",
        "cuentaOrigenId": "CTA001",
        "cuentaDestinoId": "CTA002",
        "monto": 100.00,
        "comision": 5.00,
        "tipo": "TRANSFERENCIA",
        "estado": "COMPLETADA",
        "descripcion": "Transferencia de prueba"
    }
]
```

## Consultar saldo por numero de cuenta

**GET** `http://localhost:8080/api/account/search?numeroCuenta=0001-234573`

------------------------------------------------------------------------

### Respuesta Exitosa

``` json
{
    "saldo": 15000.88
}
```

## Endpoints de registro de información

## Crear cliente

**POST** `http://localhost:8080/api/client`

------------------------------------------------------------------------

### Request

``` json
{    
    "nombre": "MARCO SAAVEDRA CASTRO",
    "email": "msaavedra@gmail.com",
    "documento": "45954571"
}
```

### Respuesta Exitosa

``` json
{
    "id": "CLI004",
    "nombre": "MARCO SAAVEDRA CASTRO",
    "email": "msaavedra@gmail.com",
    "documento": "45954571"
}
```

## Crear cuenta bancaria

**POST** `http://localhost:8080/api/account`

------------------------------------------------------------------------

### Request

``` json
{
    "clienteId": "CLI004",
    "saldo": 15000.88,
    "numeroCuenta": "0001-234573",
    "estado": "ACTIVO"
}
```

### Respuesta Exitosa

``` json
{
    "cuentaId": "CTA004",
    "clienteId": "CLI004",
    "numeroCuenta": "0001-234573",
    "saldo": 15000.88,
    "estado": "ACTIVO"
}
```
## Transferencia bancaria

**POST** `http://localhost:8080/api/transaction`

------------------------------------------------------------------------

### Request

``` json
{
    "cuentaOrigenId": "CTA001",
    "cuentaDestinoId": "CTA004",
    "monto": 900.00,
    "comision": 5.00,
    "tipo": "TRANSFERENCIA",
    "descripcion": "transferencia"
}
```

### Respuesta Exitosa

``` json
{
    "transaccionId": "TXN002",
    "tipo": "TRANSFERENCIA",
    "estado": "COMPLETADA",
    "descripcion": "transferencia"
}

```



### Respuesta para Validaciones

``` json
{
    "transaccionId": "TXN002",
    "tipo": "TRANSFERENCIA",
    "estado": "CANCELADA",
    "descripcion": "Saldo insuficiente en cuenta origen"
}
```

### Respuesta para Errores

``` json
{
    "timestamp": "2025-12-09T17:44:14.1134747",
    "status": 400,
    "error": "INVALID_CLIENT_DATA",
    "message": "Las cuentas bancarias deben ser distintas",
    "path": "/api/transaction"
}
```

------------------------------------------------------------------------

## Arquitectura Hexagonal

### Dominio

-   Models: `Account`, `Client`, `Transaction`.
-   Validaciones y reglas de negocio.

### Aplicación

-   Casos de uso:
-   `CreateAccountUseCaseImpl`
-   `CreateClientUseCaseImpl`
-   `CreateTransactionUseCaseImpl`
-   `FindAccountUseCaseImpl`
-   `FindClientUseCaseImpl`
-   `FindTransactionUseCaseImpl`

### Puertos (Interfaces)

-   `AccountRepositoryPort`
-   `ClientRepositoryPort`
-   `NotificationPort`
-   `TransactionRepositoryPort`

------------------------------------------------------------------------
