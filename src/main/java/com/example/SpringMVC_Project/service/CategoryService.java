package com.example.SpringMVC_Project.service;

import com.example.SpringMVC_Project.exception.CategoryNotFoundException;
import com.example.SpringMVC_Project.model.Category;
import com.example.SpringMVC_Project.model.dto.CategoryDTO;
import com.example.SpringMVC_Project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(CategoryDTO categoryDTO) {
        Category category = DTOToCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category findByName(String name) throws CategoryNotFoundException {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category does not exist"));
    }

    public Category DTOToCategory(CategoryDTO categoryDTO) {
        return Category.builder().name(categoryDTO.getName()).build();
    }
}
