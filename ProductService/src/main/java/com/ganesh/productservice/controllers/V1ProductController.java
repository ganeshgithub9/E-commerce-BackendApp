package com.ganesh.productservice.controllers;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.DTO.ProductList;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class V1ProductController extends ProductController{

    @Autowired
    public V1ProductController(@Qualifier("thirdPartyApiProductService") ProductService productService){
        this.productService=productService;
    }

    //Example for exception handling method-1... returns standard java exception object with fields timestamp,error,message,path
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException{
//        ResponseEntity<Product> productResponseEntity= productService.getProductById(id);
//       if(productResponseEntity.getBody()==null)
//           throw new ProductNotFoundException("Product with id"+id+" not found");
        return productService.getProductById(id);
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable Long id){
       return productService.updateProduct(productDTO,id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updatePrice(@RequestBody PriceDTO priceDTO, @PathVariable Long id) throws ProductNotFoundException{
        return productService.updatePrice(priceDTO,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) throws IDNotFoundException {
        return productService.deleteProductById(id);
    }

    @GetMapping
    public /*ResponseEntity<List<Product>>*/ ResponseEntity<Product[]> getProducts(){
        return productService.getProducts();
    }

//    public ResponseEntity<Product[]> getProductsByCategory(@RequestParam("category") String category){
//      return productService.getProductsByCategory(category);
//    }

    // Example of exception handling method-2.... controller level exceptions... can return custom java object

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> throwProductNotFoundException(ProductNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }
}
