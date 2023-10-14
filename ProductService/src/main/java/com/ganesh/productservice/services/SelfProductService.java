package com.ganesh.productservice.services;


import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidatePriceDTO;
import com.ganesh.productservice.DTO.ValidateProductDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Price;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.repositories.CategoryRepository;
import com.ganesh.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService{
    ProductRepository repository;
    CategoryRepository categoryRepository;
    IDNotFoundException exception;
    SelfProductService(ProductRepository repository,CategoryRepository categoryRepository,IDNotFoundException exception){
        this.repository=repository;
        this.categoryRepository=categoryRepository;
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
    public ProductResponseDTO getProductById(UUID id) throws IDNotFoundException{
        Optional<Product> productOptional=repository.findById(id);
        Product product=productOptional.orElse(null);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=toProductResponseDTO(product);
        return dto;
    }

    public ProductResponseDTO createProduct(ValidateProductDTO productDTO) throws IDNotFoundException{
        Price price=new Price();
        price.setCurrency(productDTO.getCurrency());
        price.setPrice(productDTO.getPrice());
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setRating(productDTO.getRating());
        product.setPrice(price);
        price.setProduct(product);
        repository.save(product);
        ProductResponseDTO dto=toProductResponseDTO(product);
        return dto;
    }

    public ProductResponseDTO assignCategoryToProduct(UUID product_id, UUID category_id) throws IDNotFoundException {
        Optional<Category> category=categoryRepository.findById(category_id);
        Optional<Product> product=repository.findById(product_id);
        System.out.println("assignProductToCategory method start");
        Category category1= category.orElse(null);
        Product product1=product.orElse(null);
        if(product1==null||category1==null)
            throw exception;
        product1.setCategory(category1);
        category1.getProducts().add(product1);
        //categoryRepository.save(category1);
        repository.save(product1);
        ProductResponseDTO dto=toProductResponseDTO(product1);
        return dto;
    }

    public ProductResponseDTO updateProduct(ValidateProductDTO productDTO, UUID id) throws IDNotFoundException{
        Optional<Product> productOptional=repository.findById(id);
        Product product=productOptional.orElse(null);
        if(product==null)
            throw exception;
        product.setName(productDTO.getName());
        product.setDescription(product.getDescription());
        product.setRating(productDTO.getRating());
        product.getPrice().setPrice(productDTO.getPrice());
        product.getCategory().setName(productDTO.getName());
        repository.save(product);
        ProductResponseDTO dto=toProductResponseDTO(product);
        return dto;
    }

    public ProductResponseDTO deleteProductById(UUID id) throws IDNotFoundException {
        Optional<Product> productOptional=repository.findById(id);
        Product product=productOptional.orElse(null);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=toProductResponseDTO(product);
        repository.deleteById(id);
        return dto;
    }

    public List<ProductResponseDTO> getProducts() throws IDNotFoundException{
        List<Product> products=repository.findAll();
        List<ProductResponseDTO> responseDTOS=new ArrayList<>();
        for(Product p:products)
            responseDTOS.add(toProductResponseDTO(p));
        return responseDTOS;
    }

}
