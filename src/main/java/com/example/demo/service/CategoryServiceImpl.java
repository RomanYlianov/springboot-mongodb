package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAllCategories() {
        logger.info("get all categories");
        List<Category> categories  = repository.findAll();
        return categories;
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        logger.info("get category by name");
        if (name!=null)
        {
            Optional<Category> box = repository.findByName(name);
            return box;
        }
        else
        {
            logger.error("name is empty");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> addNewCategory(Category category) {
        logger.info("adding category");
        if (category!=null)
        {
            category = repository.save(category);
            return Optional.of(category);
        }
        else {
            logger.error("category is empty");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        logger.info("updating category");
        if (category!=null)
        {
            Optional<Category> box = repository.findById(category.getId());
            if (box.isPresent()) {
                Category entity = box.get();
                entity.setName(category.getName());
                entity.setDescription(category.getDescription());
                entity = repository.save(entity);
                return Optional.of(entity);
            }
            else {
                logger.error("category doesnt exist");
                return Optional.empty();
            }
        }
        else {
            logger.error("category is empty");
            return Optional.empty();
        }
    }

    @Override
    public void removeCategory(String id) {
        logger.info("removing category");
        if (id!=null)
            repository.deleteById(id);
    }
}
