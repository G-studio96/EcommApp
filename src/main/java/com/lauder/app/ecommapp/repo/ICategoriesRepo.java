package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoriesRepo extends JpaRepository<CategoriesModel, Long> {


    Optional<CategoriesModel> findByCategoryId(Long id);

    List<CategoriesModel> findByCategoryName(CategoriesModel.CategoryName categoryName);


}
