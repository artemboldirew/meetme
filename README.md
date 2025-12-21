# Shopik - Backend для интернет-магазина
Ссылка на видео: https://youtu.be/BWTvvQPUI7s

REST API для управления заказами в интернет-магазине. Предоставляет эндпоинты для создания и управления заказами.


Некоторые запросы требуют заголовка customer-id с UUID покупателя. Ожидается, что запрос приходит после прохождения сервиса авторизации.

## Основные эндпоинты
### Управление заказами
POST /orders - Создать новый заказ (требует customer-id)

GET /orders/{id} - Получить заказ по ID (требует customer-id)

PATCH /orders/{id} - Обновить статус заказа

### Управление позициями заказов
PATCH /orders/items/{itemId} - Обновить позицию заказа

GET /orders/items/{itemId} - Получить позицию заказа (требует customer-id)

## Технологии
Java 17+, Spring Boot 3, Spring Data JPA

PostgreSQL, Swagger/OpenAPI 3, Liquibase

## Документация
После запуска приложения документация доступна по адресу: http://localhost:8080/api/swagger-ui/index.html