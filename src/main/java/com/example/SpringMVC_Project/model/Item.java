package com.example.SpringMVC_Project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Item {
    @Id
    private String itemCode;
    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;
    private boolean isNotSold;
}
