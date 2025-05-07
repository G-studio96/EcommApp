package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.model.CategoriesModel;
import com.lauder.app.ecommapp.repo.ICategoriesRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    private final ICategoriesRepo categoriesRepo;

    public CategoriesService(ICategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    public List<CategoriesModel> listCategories() {
        return categoriesRepo.findAll();
    }

    public void createCategory(CategoriesModel categories) {
        categoriesRepo.save(categories);
    }

    public List<CategoriesModel> readCategory(CategoriesModel.CategoryName categories) {
        return  categoriesRepo.findByCategoryName(categories);
    }

   public void updateCategory(Long categoryId, CategoriesModel newCategory) {
         Optional<CategoriesModel> categoriesModel = categoriesRepo.findByCategoryId(categoryId);
         if(categoriesModel.isPresent()) {
         CategoriesModel category  = categoriesModel.get();
         category.setCategoryName(newCategory.getCategoryName());
         category.setCreatedAt(LocalDateTime.now());
         categoriesRepo.save(category);
         } else {
             throw new RuntimeException("Category not found with id: " + categoryId);
         }
   }

}
