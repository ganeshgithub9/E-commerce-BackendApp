package com.ganesh.productservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ProductResponseDTO {
    UUID uuid;
    String name;
    String description;
    Double rating;
    Double price;
    String category;
    String currency;
}
