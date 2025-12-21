/**
 * Сущность категории товаров.
 * Используется для классификации продуктов в интернет-магазине.
 * Поддерживает автоматическое управление временными метками создания и обновления.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Product
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
@Table(name = "categories")
public class Category {
    /**
     * Уникальный идентификатор категории
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Название категории (обязательное)
     */
    @Column(nullable = false)
    private String name;

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

    public Category(String name) {
        this.name = name;
    }
}
