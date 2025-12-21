/**
 * Маппер для преобразования сущности {@link Order} в DTO {@link OrderDTO} и обратно.
 * Используется для конвертации данных заказа между слоем сущностей и слоем передачи данных.
 * Включает преобразование идентификаторов связанных сущностей.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Order
 * @see OrderDTO
 * @see OrderItemMapper
 */
package ru.vsu.cs.boldyrev.shopik.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.boldyrev.shopik.dto.OrderDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Order;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = OrderItemMapper.class
)
public interface OrderMapper {
    /**
     * Преобразует сущность заказа в DTO объект.
     * Маппит идентификаторы связанных сущностей в отдельные поля DTO.
     *
     * @param order сущность заказа для преобразования
     * @return DTO объект заказа
     */
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "pickupPoint.id", target = "pickupPointId")
    OrderDTO toDto(Order order);

    /**
     * Преобразует DTO объект заказа в сущность.
     * Создает временные сущности с установленными идентификаторами.
     *
     * @param dto DTO объект заказа для преобразования
     * @return сущность заказа
     */
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "pickupPointId", target = "pickupPoint.id")
    Order toEntity(OrderDTO dto);
}