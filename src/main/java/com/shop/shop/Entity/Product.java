package com.shop.shop.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "products")
    @JsonBackReference
    private List<Order> orders;

    private String description;
    private String category;

}
