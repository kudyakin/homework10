package com.kudiukin.homework9.service;

import com.kudiukin.homework9.NotFoundException;
import com.kudiukin.homework9.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product createProduct(String name, BigDecimal price, Long shopId) throws NotFoundException;

    Product getProductById(Long productId) throws NotFoundException;

    Product updateProduct(Long productId, String name, BigDecimal price, Long shopId);

    void deleteProduct(Long productId) throws NotFoundException;

    List<Product> getAllProducts();

}