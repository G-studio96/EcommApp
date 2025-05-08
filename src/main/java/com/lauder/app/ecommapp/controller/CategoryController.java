package com.lauder.app.ecommapp.controller;


import com.lauder.app.ecommapp.model.CategoriesModel;
import com.lauder.app.ecommapp.repo.ICategoriesRepo;
import com.lauder.app.ecommapp.service.CategoriesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final Logger logger = LoggerFactory.getLogger(ICategoriesRepo.class);

    private final CategoriesService categoriesService;


    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<CategoriesModel>> getCategories(@PathVariable String name) {
        logger.info("List of Category {}",  name);
        List<CategoriesModel> body = categoriesService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<CategoriesModel> updateCategory(@PathVariable long categoryId, @Valid @RequestBody CategoriesModel categoriesModel) {

        if (Objects.nonNull(categoriesService.readCategory(CategoriesModel.COLLECTION.valueOf(String.valueOf(categoryId))))) {
            categoriesService.updateCategory(categoryId, categoriesModel);
            logger.info("Category updated correctly {}" , categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.info("Category Id is not found {}", categoryId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
