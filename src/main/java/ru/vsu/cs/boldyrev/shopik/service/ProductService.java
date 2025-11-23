package ru.vsu.cs.boldyrev.shopik.service;

import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.shopik.entity.Product;

@Service
public class ProductService {

    public Product addProduct() {
        return new Product();
    }
}
