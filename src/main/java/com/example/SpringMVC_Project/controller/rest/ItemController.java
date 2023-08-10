package com.example.SpringMVC_Project.controller.rest;

import com.example.SpringMVC_Project.model.Item;
import com.example.SpringMVC_Project.model.dto.ItemDTO;
import com.example.SpringMVC_Project.repository.ItemRepository;
import com.example.SpringMVC_Project.repository.ProductRepository;
import com.example.SpringMVC_Project.service.ItemService;
import com.example.SpringMVC_Project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/item")
    public ResponseEntity<List <Item>> findAllItems() {
        return ResponseEntity
                .ok()
                .body(itemRepository.findAll());
    }

    @PostMapping("/item/save")
    public ResponseEntity<Item> saveProduct(@RequestBody @Valid ItemDTO itemDTO) {
        return ResponseEntity
                .ok()
                .body(itemService.saveItem(itemDTO));
    }

    @GetMapping("/item/fbn")
    public ResponseEntity<Item> findByName(@RequestBody ItemDTO itemDTO) {
        return ResponseEntity
                .ok()
                .body(itemService.findByProduct(itemDTO));
    }
}