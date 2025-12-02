package ru.vsu.cs.boldyrev.shopik.dictionary;

public enum PaymentStatus {
    PENDING,        // Ожидает оплаты
    PAID,           // Оплачено
    FAILED,         // Ошибка оплаты
    REFUNDED,       // Возвращено
    CANCELLED       // Отменено
}
