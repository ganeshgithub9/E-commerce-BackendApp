package com.ganesh.productservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.nio.DoubleBuffer;
@Setter
@Getter
public class Product {
    Long id;
    String title,description,image,category;
    Double price;
    Rating rating;
}
