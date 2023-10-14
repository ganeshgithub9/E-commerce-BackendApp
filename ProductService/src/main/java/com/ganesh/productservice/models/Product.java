package com.ganesh.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    String name;
    String description;
    Integer rating;
    @ManyToOne
    Category category;
    @ManyToMany(mappedBy = "productList")
    List<Order> orderList;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    Price price;
}
