package com.ganesh.productservice.repositories;

import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategory(Category c);
}
