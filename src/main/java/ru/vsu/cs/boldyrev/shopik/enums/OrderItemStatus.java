package ru.vsu.cs.boldyrev.shopik.enums;

public enum OrderItemStatus {
    PENDING,        // Ожидает обработки
    CONFIRMED,      // Подтверждено
    PREPARING,      // Готовится/комплектуется
    SHIPPED,        // Отправлено
    DELIVERED,      // Доставлено
    RECEIVED,       // Получено
    CANCELLED,      // Отменено
    REFUNDED
}
