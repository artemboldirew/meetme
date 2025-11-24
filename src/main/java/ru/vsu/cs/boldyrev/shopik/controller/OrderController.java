package ru.vsu.cs.boldyrev.shopik.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.boldyrev.shopik.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public void createOrder() {

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
