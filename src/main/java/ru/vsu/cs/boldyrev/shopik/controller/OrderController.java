package ru.vsu.cs.boldyrev.shopik.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Order;
import ru.vsu.cs.boldyrev.shopik.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public Order createOrder(@Valid @RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping("/{id}")
    public void getOrder(@PathVariable Long id) {

    }

    @PutMapping
    public void updateOrder() {

    }

    @DeleteMapping("")
    public void deleteOrder() {

    }
}
