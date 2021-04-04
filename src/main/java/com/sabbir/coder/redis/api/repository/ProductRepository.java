package com.sabbir.coder.redis.api.repository;

import com.sabbir.coder.redis.api.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private static final String HASH_KEY = "Product";

    @Autowired
    RedisTemplate redisTemplate;

    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAllProduct() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product findByProductId(int id) {
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteById(int id) {
        redisTemplate.delete(id);
        return "Product with Id deleted !! -" + id;
    }
}
