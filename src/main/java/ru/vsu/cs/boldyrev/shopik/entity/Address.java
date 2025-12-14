package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String region;
    private String city;
    private String district;
    private String street;
    private String house;
    private String building;
    private String apartment;
    private String postalCode;
}