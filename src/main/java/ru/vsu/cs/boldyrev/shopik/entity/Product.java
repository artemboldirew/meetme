/**
 * Сущность продукта в системе интернет-магазина.
 * Содержит информацию о товаре, включая артикул, название, цену, категорию и описания.
 * Поддерживает автоматическое управление временными метками создания и обновления.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Category
 */
package ru.vsu.cs.boldyrev.shopik.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    /**
     * Уникальный идентификатор продукта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Артикул продукта (уникальный, обязательный)
     */
    @Column(unique = true, nullable = false)
    private String sku;

    /**
     * Название продукта (обязательное)
     */
    @Column(nullable = false)
    private String name;

    /**
     * Цена продукта с точностью до 2 знаков после запятой
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Категория, к которой принадлежит продукт
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Полное описание продукта (до 1000 символов)
     */
    @Column(length = 1000)
    private String description;

    /**
     * Краткое описание продукта (до 500 символов)
     */
    @Column(name = "short_description", length = 500)
    private String shortDescription;

    /**
     * Уникальный человеко-читаемый идентификатор продукта для URL (обязательный)
     */
    @Column(nullable = false, unique = true)
    private String slug;

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