package com.ganesh.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    String title,description,image,category;
    Double price;
    Rating rating;
}
