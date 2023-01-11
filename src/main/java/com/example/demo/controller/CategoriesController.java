package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories()
    {
        logger.info("get all categories");
        List<Category> list = categoryService.getAllCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name)
    {
        logger.info("get category by name");
        if (name!=null)
        {
            Optional<Category> box = categoryService.getCategoryByName(name);
            if (box.isPresent())
            {
                return new ResponseEntity<>(box.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else {
            logger.error("name is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Category> addCategory(@RequestBody Category category)
    {
        logger.info("add new category");
        if (category!=null)
        {
            Optional<Category> box = categoryService.addNewCategory(category);
            if (box.isPresent())
            {
                return new ResponseEntity<>(box.get(), HttpStatus.CREATED);
            }
            else {
                logger.error("adding failed");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else {
            logger.error("category is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category)
    {
        logger.info("updating category");
        if (category!=null)
        {
            Optional<Category> box = categoryService.updateCategory(category);
            if (box.isPresent())
            {
                return new ResponseEntity<>(box.get(), HttpStatus.CREATED);
            }
            else {
                logger.error("updating failed");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else {
            logger.error("category is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable String id)
    {
        logger.info("removing category");
        if (id!=null)
            categoryService.removeCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
