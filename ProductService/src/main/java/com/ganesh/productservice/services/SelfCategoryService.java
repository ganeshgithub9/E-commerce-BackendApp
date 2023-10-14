package com.ganesh.productservice.services;


import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.repositories.CategoryRepository;
import com.ganesh.productservice.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfCategoryService")
public class SelfCategoryService{
    CategoryRepository repository;
    ProductRepository productRepository;
    IDNotFoundException exception;
    SelfCategoryService(CategoryRepository repository,ProductRepository productRepository, IDNotFoundException exception){
        this.repository=repository;
        this.productRepository=productRepository;
        this.exception=exception;
    }

    ProductResponseDTO toProductResponseDTO(Product product) throws IDNotFoundException {
        if(product==null)
            throw exception;
        ProductResponseDTO dto= ProductResponseDTO.builder().uuid(product.getUuid()).name(product.getName())
                .description(product.getDescription()).rating(product.getRating()).price(product.getPrice().getPrice())
                .currency(product.getPrice().getCurrency()).category(product.getCategory()!=null?product.getCategory().getName():null).build();
        return dto;
    }
    public Category createCategory(ValidateCategoryDTO dto){
        Category category=new Category();
        category.setName(dto.getName());
        repository.save(category);
        return category;
    }
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> list=repository.findAll();
        List<CategoryResponseDTO> result=new ArrayList<>();
        for(Category c:list)
            result.add(toCategoryResponseDTO(c));
        return result;
    }

    private CategoryResponseDTO toCategoryResponseDTO(Category c) {
        CategoryResponseDTO dto= CategoryResponseDTO.builder().uuid(c.getUuid()).category(c.getName()).build();
        return dto;
    }


    public List<ProductResponseDTO> getProductsByCategory(UUID uuid) throws IDNotFoundException{
        Optional<Category> categoryOptional=repository.findById(uuid);
        Category category=categoryOptional.orElse(null);
        if(category==null)
            throw exception;
        System.out.println("self category service");
        List<Product> list=productRepository.findAllByCategory(category);
        List<ProductResponseDTO> list1=new ArrayList<>();
        for(Product product:list)
            list1.add(toProductResponseDTO(product));
        return list1;
    }

}
