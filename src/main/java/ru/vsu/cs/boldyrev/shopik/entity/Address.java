package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;      // Россия
    private String region;       // Московская область
    private String city;         // Москва
    private String district;     // Южное Бутово
    private String street;       // ул. Ленина
    private String house;        // 10
    private String building;     // корп. 2
    private String apartment;    // кв. 15
    private String postalCode;   // 117042
}