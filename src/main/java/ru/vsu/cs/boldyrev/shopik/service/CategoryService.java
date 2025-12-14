package ru.vsu.cs.boldyrev.shopik.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.shopik.dto.category.CreateCategoryDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Category;
import ru.vsu.cs.boldyrev.shopik.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CreateCategoryDTO dto) {
        Optional<Category> cat = categoryRepository.findByName(dto.getName());
        if (cat.isEmpty()) {
            Category newCategory = new Category(dto.getName());
            return categoryRepository.save(newCategory);
        }
        return cat.get();
    }
}
