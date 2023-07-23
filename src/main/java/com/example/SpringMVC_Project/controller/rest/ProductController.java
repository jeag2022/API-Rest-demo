package com.example.SpringMVC_Project.controller.rest;

import com.example.SpringMVC_Project.model.Product;
import com.example.SpringMVC_Project.model.dto.ProductDTO;
import com.example.SpringMVC_Project.repository.ProductRepository;
import com.example.SpringMVC_Project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity
                .ok()
                .body(productRepository.findAll());
    }

    @GetMapping("/product/fbn")
    public ResponseEntity<Product> findByName(@RequestBody ProductDTO productDTO) {
        return  ResponseEntity
                .ok()
                .body(productService.findByName(productDTO.getName()));
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        return  ResponseEntity
                .ok()
                .body(productService.saveProduct(productDTO));
    }
}

