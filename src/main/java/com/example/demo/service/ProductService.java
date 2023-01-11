package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductByName(String name);

    Optional<Product> addProduct(Product product);

    Optional<Product> updateProduct(Product product);

    void removeProduct(String id);
}
