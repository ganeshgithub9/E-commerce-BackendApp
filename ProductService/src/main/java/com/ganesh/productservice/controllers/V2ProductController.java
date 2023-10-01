//package com.ganesh.productservice.controllers;
//
//import com.ganesh.productservice.DTO.Product;
//import com.ganesh.productservice.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("lion")
//public class V2ProductController extends ProductController{
//
//    @Autowired
//    public V2ProductController(@Qualifier("fakeStoreApiProductService") ProductService productService){
//        this.productService=productService;
//    }
//
//    //@GetMapping("/dog/{id}")
//    //@PostMapping("/post")
//    public Product getProductById( Long id){
//        return productService.getProductById(id);
//    }
//}
