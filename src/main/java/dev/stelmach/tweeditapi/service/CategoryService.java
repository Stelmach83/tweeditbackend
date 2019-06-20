package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategoryById(Long id);

    void saveCategory(Category category);

}
