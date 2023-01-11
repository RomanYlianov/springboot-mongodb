package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
    {
        logger.info("get all products");
        List<Product> list = service.getAllProducts();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name)
    {
        logger.info("get product by name");
        if (name!=null)
        {
            Optional<Product> box = service.getProductByName(name);
            if (box.isPresent())
            {
                return new ResponseEntity(box.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        else {
            logger.error("name is empty");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        logger.info("adding product");
        if (product!=null)
        {
            Optional<Product> box = service.addProduct(product);
            if (box.isPresent())
            {
                return new ResponseEntity(box.get(), HttpStatus.CREATED);
            }
            else {
                logger.error("adding failed");
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        else
        {
            logger.error("product is empty");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
    {
        logger.info("updating product");
        if (product!=null)
        {
            Optional<Product> box = service.updateProduct(product);
            if (box.isPresent())
            {
                return new ResponseEntity(box.get(), HttpStatus.CREATED);
            }
            else {
                logger.error("updating failed");
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        else {
            logger.error("product is empty");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable String  id)
    {
        logger.info("removing product");
        service.removeProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
