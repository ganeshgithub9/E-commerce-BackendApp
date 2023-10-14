package com.ganesh.productservice.controllers;


import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidatePriceDTO;
import com.ganesh.productservice.DTO.ValidateProductDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.ProductService;
import com.ganesh.productservice.services.SelfProductService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController{
    SelfProductService selfProductService;
    ProductController(SelfProductService selfProductService){
        this.selfProductService=selfProductService;
    }
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) throws IDNotFoundException {
        ProductResponseDTO dto=selfProductService.getProductById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ValidateProductDTO productDTO) throws IDNotFoundException {
        ProductResponseDTO dto=selfProductService.createProduct(productDTO);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @PatchMapping("/{product_id}/categories/{category_id}")
    ResponseEntity<ProductResponseDTO> assignCategoryToProduct(@PathVariable UUID product_id, @PathVariable UUID category_id) throws IDNotFoundException {
        return new ResponseEntity<>(selfProductService.assignCategoryToProduct(product_id,category_id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ValidateProductDTO productDTO,@PathVariable UUID id) throws IDNotFoundException {
        ProductResponseDTO dto=selfProductService.updateProduct(productDTO,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    ResponseEntity<Product> updatePrice(@Valid @RequestBody ValidatePriceDTO priceDTO, @PathVariable UUID id) throws IDNotFoundException {
//        return selfProductService.updatePrice(priceDTO,id);
//    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable UUID id) throws IDNotFoundException {
        ProductResponseDTO dto=selfProductService.deleteProductById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDTO>> getProducts() throws IDNotFoundException{
        List<ProductResponseDTO> products=selfProductService.getProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
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

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> throwProductNotFoundException(ProductNotFoundException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
