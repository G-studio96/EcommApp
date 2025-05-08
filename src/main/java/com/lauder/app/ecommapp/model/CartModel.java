package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
@Table(name = "cart")
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID", unique = true)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private UsersModel customerId;

    @ManyToOne
    @JoinColumn(name ="ID")
    private ProductModel productId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NAME")
    private ProductModel productName;

    @Min(1)
    @Column(name = "QUANTITY")
    private int quantity;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItemModel> cartItemModels  = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "PROMOTION_ID")
    private PromotionModel promotion;
    @Positive
    @Column(name = "PRICE")
    private BigDecimal price;


    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "CREATED_AT")
    private LocalDateTime localDateTime;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedTime;


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public UsersModel getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UsersModel customerId) {
        this.customerId = customerId;
    }

    public ProductModel getProductId() {
        return productId;
    }

    public void setProductId(ProductModel productId) {
        this.productId = productId;
    }

    public ProductModel getProductName() {
        return productName;
    }

    public void setProductName(ProductModel productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<CartItemModel> getCartItemModels() {
        return cartItemModels;
    }

    public void setCartItemModels(Set<CartItemModel> cartItemModels) {
        this.cartItemModels = cartItemModels;
    }

    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public PromotionModel getPromoCode() {
        return promotion;
    }

    public void setPromoCode(PromotionModel promoCode) {
        this.promotion = promoCode;
    }

    public PromotionModel getDiscount() {
        return promotion;
    }

    public void setDiscount(PromotionModel discount) {
        this.promotion = discount;
    }

    public PromotionModel getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionModel promotion) {
        this.promotion = promotion;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Transactional
    public void addProduct(ProductModel product, int quantity) {
        Optional<CartItemModel> existingItem = cartItemModels
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(product.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItemModel itemModel = existingItem.get();
            itemModel.setQuantity(itemModel.getQuantity() + quantity);
        } else {
            CartItemModel newItem = new CartItemModel();
            newItem.setCart(this);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());
            cartItemModels.add(newItem);
        }

        updateTotal();
    }

    @Transactional
    public void removeProduct(Long productId) {
        Iterator<CartItemModel> iterator = this.cartItemModels.iterator();

        while (iterator.hasNext()) {
            CartItemModel item = iterator.next();
            if (item.getProduct().getProductId().equals(productId)) {
                iterator.remove();
                break;
            }
        }

        if (this.cartItemModels.isEmpty()) {
            this.setTotal(BigDecimal.ZERO);
        } else {
            updateTotal();
        }
    }

    @Transactional
    public void updateProductQuantity(Long productId, int quantity) {
        for (CartItemModel item : this.cartItemModels) {
            if (item.getProduct().getProductId().equals(productId)) {
                if (quantity <= 0) {
                    removeProduct(productId);
                } else {
                    item.setQuantity(quantity);
                    updateTotal();
                }
                break;
            }
        }
    }

    @Transactional
    public void updateTotal() {
        BigDecimal total = cartItemModels.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.setTotal(total);
    }
    @Transactional
    public void discountTotal() {
        updateTotal();
        if (promotion != null) {
            float discount = promotion.getDiscount();
            BigDecimal discounted = this.total.multiply(BigDecimal.valueOf(1 - discount / 100));
            this.setTotal(discounted);
        }
    }

}
