package ru.vsu.cs.boldyrev.shopik.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.boldyrev.shopik.dto.ProductDTO;
import ru.vsu.cs.boldyrev.shopik.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return "";
    }


    @GetMapping("")
    public String testHello() {
        return "Hello";
    }

    @PostMapping("")
    public void addProduct(ProductDTO dto) {

    }

    @PutMapping("/{id}")
    public void updateProduct(ProductDTO dto) {

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

    }

}
