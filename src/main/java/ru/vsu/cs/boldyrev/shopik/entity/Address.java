/**
 * Встраиваемый объект адреса.
 * Используется для представления почтового адреса в других сущностях.
 * Содержит поля для страны, региона, города, улицы, дома и других компонентов адреса.
 *
 * @author Boldyrev
 * @version 1.0
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    /** Страна */
    private String country;
    /** Регион/область */
    private String region;
    /** Город */
    private String city;
    /** Район города */
    private String district;
    /** Улица */
    private String street;
    /** Номер дома */
    private String house;
    /** Строение/корпус */
    private String building;
    /** Номер квартиры/офиса */
    private String apartment;
    /** Почтовый индекс */
    private String postalCode;
}