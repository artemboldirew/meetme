/**
 * Сущность покупателя (клиента) интернет-магазина.
 * Хранит контактную информацию и личные данные покупателя.
 * Поддерживает автоматическое управление временными метками создания и обновления.
 *
 * @author Boldyrev
 * @version 1.0
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    /**
     * Уникальный идентификатор покупателя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Номер телефона покупателя (уникальный, обязательный)
     */
    @Column(nullable = false, unique = true)
    private String phone;

    /**
     * Email адрес покупателя (уникальный)
     */
    @Column(unique = true)
    private String email;

    /**
     * Имя покупателя (до 100 символов)
     */
    @Column(length = 100)
    private String firstName;

    /**
     * Фамилия покупателя (до 100 символов)
     */
    @Column(length = 100)
    private String lastName;

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