package ru.vsu.cs.boldyrev.shopik.web.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderDTO;
import ru.vsu.cs.boldyrev.shopik.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    public static final String BASE_URL = "/orders";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @PostMapping("")
    public OrderDTO createOrder(@RequestBody CreateOrderDTO dto, @RequestHeader("customer-id") UUID customerId) {
        return orderService.createOrder(dto, customerId);
    }

    @GetMapping("")
    public String getOrderById() {
        return "Hello";
    }

    @PatchMapping
    public void updateOrderById() {

    }

    @DeleteMapping
    public void deleteOrderById() {

    }
}
