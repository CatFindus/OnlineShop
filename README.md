# Online-Shop Management System

## Описание
Online Shop Management System - это система управления заказами для интернет-магазина, которая позволяет пользователям управлять заказами, клиентами и товарами. Система включает в себя несколько микросервисов, каждый из которых отвечает за свою часть функциональности.

## Основные компоненты
- **Gateway Service**(функциональность в разработке): Маршрутизация запросов и агрегация данных.
- **User Service**: Управление пользователями и их ролями.
  - http://localhost:9001/swagger/swagger-ui/index.html
- **Product Service**: Управление товарами.
  - http://localhost:9002/swagger/swagger-ui/index.html
- **Order Service**: Обработка заказов.
  - http://localhost:9003/swagger/swagger-ui/index.html
- **Rates Service**: Получение и предоставление актуальных курсов валют.
  - http://localhost:9004/swagger/swagger-ui/index.html
- **Notification Service**(функциональность в разработке): Уведомления пользователей.
- **Statistics Service**(функциональность в разработке): Для сбора статистики об товарах и покупках пользователей
- **Payment Service**(функциональность в разработке): Проведение платежных операций пользователей
- **Scheduler Service**(функциональность в разработке): Для запуска отложенных задач
- **Auth Service**(функциональность в разработке): Для авторизации/аунтефикации пользователей(Keycloak)

## Технологии
- Spring Boot
- PostgreSQL/MongoDB
- Kafka
- Redis
- Docker, DockerCompose

### Запуск
```
docker-compose up --build
```
