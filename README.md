# 🏗 Hexagonal Backend Ecosystem

A modern, event-driven microservices architecture implemented with **Spring Boot** and **Hexagonal Architecture** principles.

---

## 🌟 Overview

This project demonstrates a distributed system where multiple services interact through asynchronous messaging events (Kafka and RabbitMQ) while maintaining a clean separation of concerns using the Ports & Adapters pattern.

| Service | Responsibility | Messaging | Database |
| :--- | :--- | :--- | :--- |
| **[Pedidos](./pedidos)** | Order orchestration & creation | Kafka (Producer) | MongoDB |
| **[Inventarios](./inventarios)** | Stock management | Kafka (Consumer) | MongoDB |
| **[Notificaciones](./notificaciones)** | User alerts & messages | RabbitMQ (Consumer) | - |

---

## 📐 Architecture Philosophy

The entire ecosystem is built on **Hexagonal Architecture**, ensuring that the business logic (Domain) remains independent of external frameworks, databases, and messaging systems (Infrastructure).

### System Flow
1. **Pedidos** receives a REST request to create an order.
2. **Pedidos** saves the order to **MongoDB** and publishes an event to **Kafka**.
3. **Inventarios** consumes the event from **Kafka** and updates product stock in its own **MongoDB**.
4. **Notificaciones** (optional integration) handles delivery alerts via **RabbitMQ**.

---

## 🛠 Prerequisites

- **Java 17**
- **Maven 3.8+**
- **Docker & Docker Compose**
- **MongoDB**, **Kafka**, and **RabbitMQ**

---

## 🚀 Getting Started

### 1. Infrastructure Setup
Use the Docker Compose provided in the `pedidos` project to start the core infrastructure:
```bash
cd pedidos
docker-compose up -d
```

### 2. Running Services
Each service can be started independently using Maven:
```bash
# In each service directory
mvn spring-boot:run
```

| Service | Port | Documentation |
| :--- | :--- | :--- |
| Pedidos | `8080` | [Swagger UI](http://localhost:8080/swagger-ui.html) |
| Inventarios | `8081` | [Swagger UI](http://localhost:8081/swagger-ui.html) |

---

## 📁 Project Structure

```text
.
├── pedidos         # Order management service (Kafka Producer)
├── inventarios     # Stock management service (Kafka Consumer)
└── notificaciones  # Notification service (RabbitMQ Consumer)
```


