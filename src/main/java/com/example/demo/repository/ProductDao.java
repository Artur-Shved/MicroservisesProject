//package com.example.demo.repository;
//
//import com.example.demo.entity.Product;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//
//@Repository
//public class ProductDao implements ProductRepository{
//
//    private RedisTemplate<String, Product> redisTemplate;
//
//    private HashOperations hashOperations;
//
//    public ProductDao(RedisTemplate<String, Product> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Override
//    public void save(Product product) {
//        hashOperations.put("DRIVERS", product.getResource(), product);
//    }
//
//    @Override
//    public Map<String,Product> findAll() {
//        return hashOperations.entries("DRIVERS");
//    }
//
//    @Override
//    public Product findByName(String name) {
//        return (Product)hashOperations.get("DRIVERS", name);
//    }
//
//    @Override
//    public void update(Product product) {
//        save(product);
//    }
//
//    @Override
//    public void delete(String name) {
//        hashOperations.delete("DRIVERS", name);
//    }
//}
