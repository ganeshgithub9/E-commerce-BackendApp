package com.ganesh.productservice.controllers;

import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    CategoryController(@Qualifier("thirdPartyApiCategoryService") CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping
    public ResponseEntity<String[]> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{category}")
    public ResponseEntity<Product[]> getProductsByCategory(@PathVariable String category){
        return categoryService.getProductsByCategory(category);
    }
}
