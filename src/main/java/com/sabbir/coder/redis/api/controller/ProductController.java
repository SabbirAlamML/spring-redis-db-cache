package com.sabbir.coder.redis.api.controller;

import com.sabbir.coder.redis.api.entity.Product;
import com.sabbir.coder.redis.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableCaching
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAllProduct();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id) {
        return productRepository.findByProductId(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        productRepository.deleteById(id);
        return "Product with id deleted: " + id;
    }
}
