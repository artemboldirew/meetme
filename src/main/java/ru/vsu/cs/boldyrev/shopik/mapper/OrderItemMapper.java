/**
 * Маппер для преобразования сущности {@link OrderItem} в DTO {@link OrderItemDTO} и обратно.
 * Используется для конвертации данных позиции заказа между слоем сущностей и слоем передачи данных.
 * Обрабатывает связи с товарами и игнорирует обратную ссылку на заказ при преобразовании в сущность.
 *
 * @author Boldyrev
 * @version 1.0
 * @see OrderItem
 * @see OrderItemDTO
 */
package ru.vsu.cs.boldyrev.shopik.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.boldyrev.shopik.dto.OrderItemDTO;
import ru.vsu.cs.boldyrev.shopik.entity.OrderItem;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderItemMapper {
    /**
     * Преобразует сущность позиции заказа в DTO объект.
     * Маппит идентификатор товара в отдельное поле DTO.
     *
     * @param item сущность позиции заказа для преобразования
     * @return DTO объект позиции заказа
     */
    @Mapping(source = "product.id", target = "productId")
    OrderItemDTO toDto(OrderItem item);

    /**
     * Преобразует DTO объект позиции заказа в сущность.
     * Создает временную сущность товара с установленным идентификатором.
     * Игнорирует поле заказа во избежание циклических зависимостей.
     *
     * @param dto DTO объект позиции заказа для преобразования
     * @return сущность позиции заказа
     */
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);
}