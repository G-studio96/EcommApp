package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.ProductModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface IProductRepo extends JpaRepository<ProductModel, Long> {

    @Query("SELECT p FROM ProductModel  p WHERE p.quantity > 0")
    List<ProductModel> findAvailableProducts();

    @Query("SELECT p FROM ProductModel  p WHERE p.productName LIKE %:name%")
    List<ProductModel> searchByProductName(@Param("name") String name);

    @Override
    Page<ProductModel> findAll(Pageable pageable);

    Optional<ProductModel> findByProductId(Long productId);

    @Cacheable("products_by_quantity")
    Page<ProductModel> findByQuantityGreaterThanEqual(int Quantity, Pageable pageable);


    Optional<ProductModel> findByQuantityLessThanEqual(int Quantity);

    @Query("SELECT p from  ProductModel p WHERE p.price BETWEEN :minPrice and :maxPrice")
   Page<ProductModel> findByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

}
