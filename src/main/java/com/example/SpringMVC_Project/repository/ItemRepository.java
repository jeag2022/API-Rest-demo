package com.example.SpringMVC_Project.repository;

import com.example.SpringMVC_Project.model.Item;
import com.example.SpringMVC_Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {
    Optional<Item> findByProduct(Product product);
}
