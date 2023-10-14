package com.ganesh.productservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class CategoryResponseDTO {
    UUID uuid;
    String category;
}
