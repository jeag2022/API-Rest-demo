package com.example.SpringMVC_Project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer product_id;
    private String name;
    @OneToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer price;
    private Integer stock;
}