package ru.vsu.cs.boldyrev.shopik.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.boldyrev.shopik.dto.category.CreateCategoryDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Category;
import ru.vsu.cs.boldyrev.shopik.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public Category createCategory(@RequestBody CreateCategoryDTO dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping("/{id}")
    public void getCategory(@PathVariable Long id) {

    }

    @PutMapping
    public void updateCategory() {

    }

    @DeleteMapping("")
    public void deleteCategory() {

    }
}
