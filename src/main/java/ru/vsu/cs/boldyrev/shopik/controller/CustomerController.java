package ru.vsu.cs.boldyrev.shopik.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @PostMapping("")
    public void createCustomer() {

    }

    @GetMapping("/{id}")
    public void getCustomer(@PathVariable Long id) {

    }

    @PutMapping
    public void updateCustomer() {

    }

    @DeleteMapping("")
    public void deleteCustomer() {

    }
}
