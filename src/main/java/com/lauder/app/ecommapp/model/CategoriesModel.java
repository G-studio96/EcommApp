package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "categories")
public class CategoriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", unique = true)
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "COLLECTION", unique = true)
    private COLLECTION collection;


    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    public enum COLLECTION {

        KINGS,
        QUEENS
    }

    public enum Men {
        AVIATOR,
        WAYFARER,
        CLUBMASTER,
        D_FRAME,
        NAVIGATOR,
        RECTANGLE,
        SQUARE,
        ROUND,


    }

    public enum Women {
        OVERSIZED,
        CAT_EYE,
        BUTTERFLY,
        HEXAGON,
        OVAL,
        WHITE_FRAME,
        YELLOW_TINTED
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public COLLECTION getCategoryName() {
        return collection;
    }

    public void setCategoryName(COLLECTION collection) {
        this.collection = collection;
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


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
