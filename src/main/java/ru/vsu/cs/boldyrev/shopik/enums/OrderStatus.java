package ru.vsu.cs.boldyrev.shopik.enums;

public enum OrderStatus {
    PENDING,        // Ожидает обработки
    CONFIRMED,      // Подтвержден
    PROCESSING,     // В обработке
    CANCELLED,      // Отменен
    COMPLETED,
}
