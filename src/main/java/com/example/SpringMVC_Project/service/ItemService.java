package com.example.SpringMVC_Project.service;

import com.example.SpringMVC_Project.exception.ItemNotFoundException;
import com.example.SpringMVC_Project.exception.ProductNotFoundException;
import com.example.SpringMVC_Project.model.Item;
import com.example.SpringMVC_Project.model.Product;
import com.example.SpringMVC_Project.model.dto.ItemDTO;
import com.example.SpringMVC_Project.repository.ItemRepository;
import com.example.SpringMVC_Project.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Item saveItem (ItemDTO itemDTO) throws ProductNotFoundException {
        Item item = DTOToItem(itemDTO);
        Product product = productRepository.findByName(itemDTO.getProduct()).orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
        Item itemSaved = itemRepository.save(item);
        updateStock(product);
        return itemSaved;
    }

    public Item findByProduct(ItemDTO itemDTO) throws ProductNotFoundException, ItemNotFoundException {
        Product product = productRepository.findByName(itemDTO.getProduct()).orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
        return itemRepository.findByProduct(product).orElseThrow(() -> new ItemNotFoundException("Item of product does not exist"));
    }

    public Item DTOToItem(ItemDTO itemDTO) throws ProductNotFoundException {
        Item item = new Item();
        item.setItemCode(itemDTO.getItemCode());
        item.setProduct(productRepository.findByName(itemDTO.getProduct()).orElseThrow(() -> new ProductNotFoundException("Product does not exist")));
        item.setNotSold(true);
        return item;
    }

    public Integer updateStock(Product product) {
        Long stock = entityManager.createQuery("SELECT COUNT(i.isNotSold) FROM Item i WHERE i.product = :product AND i.isNotSold  = true", Long.class).setParameter("product", product).getSingleResult();
        entityManager.createQuery("UPDATE Product p SET p.stock = :stock WHERE p.name = :productName")
                .setParameter("stock", stock)
                .setParameter("productName", product.getName())
                .executeUpdate();
        return stock.intValue();
    }
}
