package ru.vsu.cs.boldyrev.shopik.dictionary;

public enum OrderStatus {
    PENDING,        // Ожидает обработки
    CONFIRMED,      // Подтвержден
    PROCESSING,     // В обработке
    CANCELLED,      // Отменен
    COMPLETED,
}
