package ru.vsu.cs.boldyrev.meetme.entity;


import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String category;
    private String description;

}
