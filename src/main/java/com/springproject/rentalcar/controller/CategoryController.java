package com.springproject.rentalcar.controller;

import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

//    @GetMapping()
//    public String testApi(){
//        return "Welcome to rental car system api";
//    }

    @GetMapping()
    public List<Category> fetchAllCategories(){
        return  categoryServiceImp.fetchCategories();
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category){
        return categoryServiceImp.createCategory(category);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryServiceImp.getCategory(categoryId);
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId){
        return categoryServiceImp.updateCategory(category, categoryId);
    }

    @GetMapping("/byName/{categoryName}")
    public Category getCategoryByName(@PathVariable("categoryName") String categoryName){
        return categoryServiceImp.getCategoryByName(categoryName);
    }

    @DeleteMapping("/{categoryId}")
    public Category deleteCategory(@PathVariable("categoryId") Long categoryId){
        return categoryServiceImp.deleteCategory(categoryId);
    }

}
