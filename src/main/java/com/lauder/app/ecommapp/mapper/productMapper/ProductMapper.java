package com.lauder.app.ecommapp.mapper.productMapper;


import com.lauder.app.ecommapp.dto.request.product.ProductRequest;
import com.lauder.app.ecommapp.dto.response.product.ProductResponse;
import com.lauder.app.ecommapp.model.CategoriesModel;
import com.lauder.app.ecommapp.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductModel toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }

        ProductModel product = new ProductModel();
        product.setProductId(request.getProductId());

        if (request.getCategoryId() != null) {
            CategoriesModel category = new CategoriesModel();
            category.setCategoryId(request.getProductId());
            product.setCategoryId(category);

        }

        product.setProductName(request.getProductName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setUrl(request.getUrl());

        return product;


    }

    public ProductResponse toResponse(ProductModel productModel) {
        if (productModel == null ) {
            return null;

        }

        return new ProductResponse.Builder()
                .productId(productModel.getProductId())
                .categoryId(productModel.getCategoryId() != null ? productModel.getCategoryId().getCategoryId() : null)
                .categoryName(productModel.getCategoryId() != null ? String.valueOf(productModel.getCategoryId().getCategoryName()) : null)
                .productName(productModel.getProductName())
                .quantity(productModel.getQuantity()).
                price(productModel.getPrice())
                .createdTime(productModel.getLocalDateTime())
                .updatedTime(productModel.getUpdatedTime())
                .url(productModel.getUrl())
                .url(productModel.getUrl())
                .build();
    }

}
