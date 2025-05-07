package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "categories")
public class CategoriesModel {

    @Id
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_NAME")
    private CategoryName categoryName;



    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public enum  CategoryName {
        POTS,
        PLANTS,
        FLOWERS,
        TOOLS,
        TREES,
        SEEDS,
        ACCESSORIES
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
