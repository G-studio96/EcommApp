package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.product.ProductRequest;
import com.lauder.app.ecommapp.dto.response.product.ProductResponse;
import com.lauder.app.ecommapp.mapper.productMapper.ProductMapper;
import com.lauder.app.ecommapp.model.ProductModel;
import com.lauder.app.ecommapp.repo.IProductRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

   private final IProductRepo productRepo;

   private final ProductMapper productMapper;


    @Autowired
    public ProductService(IProductRepo iProductRepo, ProductMapper productMapper) {
        this.productRepo = iProductRepo;
        this.productMapper = productMapper;
    }

    @Cacheable("products")
    public List<ProductResponse> getAllProducts() {
        logger.info("Fetching all products");
        return productRepo.findAll()
                .stream().map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "products-page",  key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<ProductResponse> getAllProductsPaginated(Pageable pageable) {
        logger.info("Fetching products page {} with size {}", pageable.getPageNumber(), pageable.getPageSize());

        return productRepo.findAll(pageable).map(productMapper::toResponse);
    }

    @CacheEvict(value = "product", key = "#id")
    public ProductResponse getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        return productRepo.findByProductId(id)
                .map(productMapper::toResponse).
                orElseThrow(() -> new ResourceAccessException("Product not found with id: " + id));
    }

    @CacheEvict(value = {"products", "products-page", "product"}, allEntries = true)
    public ProductResponse createProduct(ProductRequest productRequest) {
        logger.info("Creating new product: {}", productRequest);

        validateProductData(productRequest);

        ProductModel productModel = productMapper.toEntity(productRequest);

        productModel.setLocalDateTime(LocalDateTime.now());

        ProductModel savedProduct = productRepo.save(productModel);

        logger.info("Created product with Id: {}" , savedProduct.getProductId());

        return productMapper.toResponse(savedProduct);
    }


    @CacheEvict(value = {"products", "products-page", "product"}, allEntries = true)
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        logger.info("Updating product with id {}: {}",id, productRequest);

        validateProductData(productRequest);

        ProductModel existingProduct = productRepo.findByProductId(id)
                .orElseThrow(() -> new ResourceAccessException("Product not found with id:" + id));

        ProductModel productModel = productMapper.toEntity(productRequest);
        productModel.setProductId(id);

        productModel.setLocalDateTime(existingProduct.getLocalDateTime());

        productModel.setUpdatedTime(LocalDateTime.now());

        ProductModel updatedProduct = productRepo.save(productModel);
        logger.info("Updated product with ID: {}", updatedProduct.getProductId());

        return  productMapper.toResponse(updatedProduct);

    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public void deleteProduct(Long id) {
        logger.info("Deleting product with id: {}", id);

        if(!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete - product not found with id: " + id);
        }

        productRepo.deleteById(id);
        logger.info("Deleted product with Id: {}", id);
    }

    @Cacheable(value = "products-by-price", key = "#minPrice.toString() + '-' + #maxPrice.toString()")
    public Page<ProductResponse> findProductsByPriceRange( BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        logger.info("Finding products with the price between {} and {}", minPrice, maxPrice);
        return productRepo.findByPriceBetween(minPrice, maxPrice, pageable).map(
                productMapper::toResponse
        );
    }

    private void validateProductData(ProductRequest productRequest) {
        if (productRequest == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }


        if (productRequest.getProductName() == null ||
                productRequest.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (productRequest.getPrice() == null ||
                productRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        if (productRequest.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }

    public List<ProductModel> getAllAvailableProducts() {
        logger.info("Available products.");
        return productRepo.findAvailableProducts();
    }


    public ProductResponse adjustStock(Long productId, int delta) {
        logger.info("Adjusting stock for productId {} by {}", productId, delta);

        ProductModel product = productRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        int newQuantity = product.getQuantity() + delta;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient stock for product: " + productId);
        }
        product.setQuantity(newQuantity);

        return productMapper.toResponse(product);
    }

    public boolean isInStock(Long productId, int requestedQuantity) {
        ProductModel product = productRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("the product is not in stock"));
        return product.getQuantity() >= requestedQuantity;
    }

}
