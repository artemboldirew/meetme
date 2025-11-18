package ru.vsu.cs.boldyrev.meetme.entity;


import jakarta.annotation.Generated;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Column(name = "compare_price")
    private BigDecimal comparePrice;
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    @Column(name = "category_id")
    private Long categoryId;
    private String description;
    @Column(name = "short_description")
    private String shortDescription;

    private String slug;
    @Column(name = "meta_title")
    private String metaTitle;
    @Column(name = "meta_description")
    private String metaDescription;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
