package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.NoSuchElementException;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchCategories() {
        List<Category> categoryList =  categoryRepository.findAll();

        if(categoryList.size() == 0){
            throw new EmptyTableException("table empty");
        }else{
            return categoryList;
        }
    }

    @Override
    public Category createCategory(Category category) {

        if(category.getType() == ""){
            throw new BusinessException(400, "Enter valid category type");
        }else {
            return categoryRepository.save(category);
        }

    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).get();

        if(category.getType() == ""){
            throw new BusinessException(400, "Enter valid category type");
        }else {
            existingCategory.setType(category.getType());
            return categoryRepository.save(existingCategory);
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        if(categoryName == ""){
            throw new BusinessException(400, "Enter valid category name");
        }else {
            Category category =  categoryRepository.findByType(categoryName);
            if(category==null){
                throw new NoSuchElementException("Category does not exists");
            }else {
                return category;
            }
        }
    }

    @Override
    public Category deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).get();

        categoryRepository.delete(category);
        return category;
    }
}
