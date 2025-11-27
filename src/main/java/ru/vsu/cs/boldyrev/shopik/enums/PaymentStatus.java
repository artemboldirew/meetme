package ru.vsu.cs.boldyrev.shopik.enums;

public enum PaymentStatus {
    PENDING,        // Ожидает оплаты
    PAID,           // Оплачено
    FAILED,         // Ошибка оплаты
    REFUNDED,       // Возвращено
    CANCELLED       // Отменено
}
