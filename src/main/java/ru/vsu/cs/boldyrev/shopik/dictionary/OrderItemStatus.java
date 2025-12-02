package ru.vsu.cs.boldyrev.shopik.dictionary;

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
