package ru.vsu.cs.boldyrev.shopik.enums;

public enum OrderStatus {
    CONFIRMED,
    PROCESSING,
    ASSEMBLING,
    ASSEMBLED,
    READY_FOR_SHIPMENT,
    SHIPPED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    ARRIVED_AT_PICKUP_POINT,
    DELIVERED,
    RECEIVED,
    FAILED_DELIVERY,
    RETURNED
}
