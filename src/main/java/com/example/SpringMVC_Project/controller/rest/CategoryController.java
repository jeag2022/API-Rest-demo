package com.example.SpringMVC_Project.controller.rest;

import com.example.SpringMVC_Project.model.Category;
import com.example.SpringMVC_Project.model.dto.CategoryDTO;
import com.example.SpringMVC_Project.repository.CategoryRepository;
import com.example.SpringMVC_Project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAllCategories() {
        return ResponseEntity
                .ok()
                .body(categoryRepository.findAll());
    }

    @PostMapping("/category/save")
    public ResponseEntity<Category> saveCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity
                .ok()
                .body(categoryService.saveCategory(categoryDTO));
    }

    @GetMapping("/category/fbn")
    public ResponseEntity<Category> findByName(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity
                .ok()
                .body(categoryService.findByName(categoryDTO.getName()));
    }
}

