package com.lauder.app.ecommapp.dto.response.shipping;

import com.lauder.app.ecommapp.model.Shipping;
import com.lauder.app.ecommapp.model.ShippingStatus;

import java.time.LocalDateTime;
import java.util.Set;

public class ResponseShippingStatus {
    private Long shippingId;

    private Set<ShippingStatus.Status> status;

    private Shipping shipping;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Set<ShippingStatus.Status> getStatus() {
        return status;
    }

    public void setStatus(Set<ShippingStatus.Status> status) {
        this.status = status;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
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
