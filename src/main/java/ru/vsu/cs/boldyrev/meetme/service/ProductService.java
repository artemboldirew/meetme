package ru.vsu.cs.boldyrev.meetme.service;

import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.meetme.entity.Product;

@Service
public class ProductService {

    public Product addProduct() {
        return new Product();
    }
}
