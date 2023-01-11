package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository catRepository;

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getAllProducts() {
        logger.info("get all products");
        List<Product> products = repository.findAll();
        for (var product: products)
        {
            Optional<Category> box = catRepository.findById(product.getCategory());
            if (box.isPresent())
            {
                product.setData(box.get());
            }
        }
        return products;
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        logger.info("get product by name");
        if (name!=null)
        {
            Optional<Product> box = repository.findByName(name);
            if (box.isPresent())
            {
                Product product = box.get();
                Optional<Category> category = catRepository.findById(product.getCategory());
                if (category.isPresent())
                {
                    product.setData(category.get());
                    return Optional.of(product);
                }
                else {
                    logger.error("category is empty");
                    return Optional.empty();
                }
            }
            else {
                return Optional.empty();
            }
        }
        else {
            logger.error("name is empty");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> addProduct(Product product) {
        logger.info("adding product");
        if (product!=null)
        {
            Optional<Category> box = catRepository.findByName(product.getData().getName());
            if (box.isPresent())
            {
                product.setCategory(box.get().getId());
            }
            product = repository.save(product);
            return Optional.of(product);
        }
        else {
            logger.error("product is empty");
            return Optional.empty();
        }

    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        logger.info("updating product");
        if (product!=null)
        {
            Optional<Product> box = repository.findById(product.getId());
            if (box.isPresent())
            {
                Product entity = box.get();
                entity.setName(product.getName());
                entity.setCurrent_count(product.getCurrent_count());
                entity.setCount(product.getCount());
                entity.setPrice(product.getPrice());
                entity.setDate(product.getDate());
                entity.setComment(product.getComment());
                Optional<Category> cat = catRepository.findById(product.getData().getId());
                if (cat.isPresent())
                {
                    entity.setCategory(cat.get().getId());
                }
                entity = repository.save(entity);
                return Optional.of(entity);
            }
            else {
                logger.error("product doesnt exist");
                return Optional.empty();
            }
        }
        else {
            logger.error("product is empty");
            return Optional.empty();
        }

    }

    @Override
    public void removeProduct(String id) {
        logger.info("removing product");
        if (id!=null)
        {
            repository.deleteById(id);
        }
    }
}
