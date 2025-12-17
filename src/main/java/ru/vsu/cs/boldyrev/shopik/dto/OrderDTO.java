package ru.vsu.cs.boldyrev.shopik.dto;

import lombok.AllArgsConstructor;
import ru.vsu.cs.boldyrev.shopik.entity.Order;
import ru.vsu.cs.boldyrev.shopik.entity.OrderItem;

import java.util.List;

@AllArgsConstructor
public class OrderDTO {
    private Order order;
    private List<OrderItem> orderItems;
}
