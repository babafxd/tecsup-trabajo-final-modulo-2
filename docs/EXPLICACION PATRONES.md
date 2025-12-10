# 🏦 SISTEMA DE GESTIÓN BANCARIA
# TRABAJO FINAL - ARQUITECTURA DE SOFTWARE

Este documento explica los **patrones de diseño utilizados** en el proyecto basado en **Arquitectura Hexagonal**, así como su relación con los **patrones creacionales, estructurales y de comportamiento**.

---

# 🧱 Arquitectura Hexagonal

La arquitectura hexagonal divide la aplicación en tres capas:

```
┌───────────────────────────────┐
│         Infraestructura        │  ← Implementa los Adaptadores
└──────────┬───────────┬─────────┘
           │           │
      Input Adapters   Output Adapters
           │           │
┌──────────▼───────────▼─────────┐
│            Aplicación           │  ← Casos de Uso, usa puertos para recursos externos
└─────────────────┬───────────────┘
                  │
┌─────────────────▼───────────────┐
│              Dominio             │  ← Entidades y reglas de negocio, Independiente al framework
└──────────────────────────────────┘
```
---

# 🧩 Patrones vistos

```
patrones
│
├── 1_creacional
│   ├── builder
│   ├── factory
│   └── singleton
│
├── 2_estructural
│   ├── adapter
│   └── facade
│
└── 3_comportamiento
    ├── observer
    └── strategy
```

A continuación, se explican **solo los patrones aplicados al proyecto de Sistema de gestión bancario**.

---

# 🟦 1. Patrones Creacionales

## 🏭 Factory (Fábrica)
Permite crear objetos sin exponer su lógica de construcción.

### ✔ Aplicación en el proyecto
Spring Boot actúa como fábrica de Beans:

```java
@Bean
public CreateTransactionUseCase createTransactionUseCase(...) {
    return new CreateTransactionUseCaseImpl(...);
}
```

## 🧩 Singleton
Permite crear una sola instancia globa, en Spring Boot los beans con @Service, @Component, @Repository ya son Singleton por defecto.




---

# 🟥 2. Patrones Estructurales

## 🔌 Adapter (Adaptador)
Traduce la interfaz esperada por el dominio a una implementación concreta.

### ✔ Aplicación
- ConsoleNotificationAdapter
- Log4jNotificationAdapter
- JpaTransactionRepositoryAdapter
- HexagonalConfig , Línea 45
---

## 🧱 Facade (Fachada)
Simplifica interacciones complejas bajo una sola interfaz.

### ✔ Aplicación
Los **Use Cases**, como el caso `CreateTransactionUseCaseImpl` que realiza numerosas validaciones, y actúa como fachada.

---

# 🟩 3. Patrones de Comportamiento

## 👁 Observer (Observador)
Reaccionar ante eventos sin acoplar actores.

### ✔ Aplicación
`NotificationPort` funciona como un observador.  
Cada adaptador es un “observador” concreto.

---

## 🎯 Strategy (Estrategia)
Define algoritmos intercambiables.

### ✔ Aplicación
Los tipos de transacción actúan como estrategias:
- Depósito
- Retiro
- Transferencia

Cada uno modifica el saldo con una lógica distinta.

---

# 📄 Conclusión

La combinación de estos patrones da como resultado un sistema desacoplado, extensible y fácil de mantener.

---
