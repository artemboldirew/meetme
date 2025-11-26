package ru.vsu.cs.boldyrev.shopik.enums;

public enum PaymentStatus {
    PENDING,
    AWAITING_PAYMENT,
    PROCESSING,
    AUTHORIZED,
    CAPTURED,
    COMPLETED,
    FAILED,
    CANCELLED,
    REFUNDED,
    PARTIALLY_REFUNDED
}
