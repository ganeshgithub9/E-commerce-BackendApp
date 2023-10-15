package com.ganesh.productservice.controllers;

import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.SelfCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    SelfCategoryService service;
    CategoryController(SelfCategoryService service){
        this.service=service;
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody ValidateCategoryDTO dto){
        Category category= service.createCategory(dto);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> list=service.getAllCategories();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{category_id}/products")
    ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable UUID category_id) throws IDNotFoundException {
        List<ProductResponseDTO> list=service.getProductsByCategory(category_id);
        //System.out.println("category controller");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
