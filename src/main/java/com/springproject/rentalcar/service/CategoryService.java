package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> fetchCategories();
    public Category createCategory(Category category);
    public Category getCategory(Long categoryId);
    public Category updateCategory(Category category, Long categoryId);
    public Category getCategoryByName(String categoryName);
    public Category deleteCategory(Long categoryId);
}
