package com.example.SpringMVC_Project.service;

import com.example.SpringMVC_Project.exception.CategoryNotFoundException;
import com.example.SpringMVC_Project.exception.ProductNotFoundException;
import com.example.SpringMVC_Project.model.Product;
import com.example.SpringMVC_Project.model.dto.ProductDTO;
import com.example.SpringMVC_Project.repository.CategoryRepository;
import com.example.SpringMVC_Project.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product saveProduct(ProductDTO productDTO) {
        Product product = DTOToProduct(productDTO);
        return productRepository.save(product);
    }

    public Product findByName(String productName) throws ProductNotFoundException {
        return productRepository
                .findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
    }

    public Product DTOToProduct(ProductDTO productDTO) throws CategoryNotFoundException {
        Product product;

        if (productRepository.findByName(productDTO.getName()).isEmpty()) {
            product = new Product().builder().stock(0).build();
        } else {
            product = productRepository.findByName(productDTO.getName()).get();
        }

        product.setName(productDTO.getName());
        product.setCategory(categoryRepository.findByName(productDTO.getCategory()).orElseThrow(() -> new CategoryNotFoundException("Category does not exist")));
        product.setPrice(productDTO.getPrice());

        return product;
    }

    public ProductDTO productToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productDTO.getName());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }
}
