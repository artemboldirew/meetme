/**
 * Сущность пункта выдачи заказов.
 * Содержит географические координаты и адрес пункта выдачи.
 * Поддерживает автоматическое управление временными метками создания и обновления.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Address
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "pickup_points")
public class PickupPoint {
    /**
     * Уникальный идентификатор пункта выдачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Географическая широта пункта выдачи (обязательное)
     */
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /**
     * Географическая долгота пункта выдачи (обязательное)
     */
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    /**
     * Адрес пункта выдачи (обязательное)
     */
    @NotNull
    @Embedded
    private Address address;

    /**
     * Дата и время создания записи (автоматически устанавливается)
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего обновления записи (автоматически обновляется)
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
