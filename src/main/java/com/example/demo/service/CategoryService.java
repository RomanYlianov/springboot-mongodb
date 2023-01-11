package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryByName(String name);

    Optional<Category> addNewCategory(Category category);

    Optional<Category> updateCategory(Category category);

    void removeCategory(String id);

}
